package ie.wit.fragments


import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ie.wit.R
/*
import ie.wit.adapters.ValetListener
*/
import ie.wit.adapters.ValetingAdapter
import ie.wit.adapters.ValetingListener
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import ie.wit.utils.*
import kotlinx.android.synthetic.main.fragment_booking_list.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class BookingListFragment : Fragment(), AnkoLogger, ValetingListener {

    lateinit var app: ValetApp
    lateinit var loader: AlertDialog
    lateinit var root: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_booking_list, container, false)

        root.recyclerView.layoutManager = LinearLayoutManager(activity)
        root.recyclerView.adapter = ValetingAdapter(app.valets, this)
        loader = createLoader(requireActivity())
        setSwipeRefresh()

        val swipeDeleteHandler = object : SwipeToDeleteCallback(requireActivity()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = root.recyclerView.adapter as ValetingAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                deleteBooking((viewHolder.itemView.tag as ValetModel).uid)
                deleteUserBooking(app.auth.currentUser!!.uid,
                    (viewHolder.itemView.tag as ValetModel).uid)
            }
        }
        val itemTouchDeleteHelper = ItemTouchHelper(swipeDeleteHandler)
        itemTouchDeleteHelper.attachToRecyclerView(root.recyclerView)

        val swipeEditHandler = object : SwipeToEditCallback(requireActivity()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onValetClick(viewHolder.itemView.tag as ValetModel)
            }
        }
        val itemTouchEditHelper = ItemTouchHelper(swipeEditHandler)
        itemTouchEditHelper.attachToRecyclerView(root.recyclerView)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BookingListFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    fun setSwipeRefresh() {
        root.swiperefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                root.swiperefresh.isRefreshing = true
                getAllBookings(app.auth.currentUser!!.uid)
            }
        })
    }

    fun checkSwipeRefresh() {
        if (root.swiperefresh.isRefreshing) root.swiperefresh.isRefreshing = false
    }

    fun deleteUserBooking(userId: String, uid: String?){
        app.database.child("user-bookings").child(userId).child(uid!!)
            .addListenerForSingleValueEvent(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot){
                        snapshot.ref.removeValue()
                    }
                    override fun onCancelled(error: DatabaseError){
                        info("Firebase Booking error : ${error.message}")
                    }
                })
    }

    fun deleteBooking(uid: String?){
        app.database.child("bookings").child(uid!!)
            .addListenerForSingleValueEvent(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot){
                        snapshot.ref.removeValue()
                    }
                    override fun onCancelled(error: DatabaseError){
                        info("Firebase Booking error : ${error.message}")
                    }
                })
    }

    override fun onValetClick(valet: ValetModel){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrame, EditFragment.newInstance(valet))
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        getAllBookings(app.auth.currentUser!!.uid)
    }

    fun getAllBookings(userId: String?){
        showLoader(loader, "Downloading Bookings from firebase")
        var bookingsList = ArrayList<ValetModel>()
        app.database.child("user-bookings").child(userId!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    info("Firebase Booking error : ${error.message}")
                }
                override fun onDataChange(snapshot: DataSnapshot){
                    val children = snapshot!!.children
                    children.forEach{
                        val booking = it.getValue<ValetModel>(ValetModel::class.java!!)
                        bookingsList.add(booking!!)
                        app.valets = bookingsList
                        root.recyclerView.adapter = ValetingAdapter(app.valets, this@BookingListFragment)
                        root.recyclerView.adapter?.notifyDataSetChanged()
                        checkSwipeRefresh()
                        hideLoader(loader)
                        app.database.child("user-bookings").child(userId!!).removeEventListener(this)
                    }
                }
            })
    }
}
