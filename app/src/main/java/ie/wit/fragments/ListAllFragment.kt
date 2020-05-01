package ie.wit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ie.wit.R
import ie.wit.adapters.ValetingAdapter
import ie.wit.adapters.ValetingListener
import ie.wit.models.ValetModel
import ie.wit.utils.*
import kotlinx.android.synthetic.main.fragment_booking_list.view.*
import org.jetbrains.anko.info

class ListAllFragment : BookingListFragment(),
    ValetingListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_booking_list, container, false)
        activity?.title = getString(R.string.menu_report_all)

        root.recyclerView.layoutManager = LinearLayoutManager(activity)
        setSwipeRefresh()

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListAllFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    override fun setSwipeRefresh() {
        root.swiperefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                root.swiperefresh.isRefreshing = true
                getAllUsersDonations()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getAllUsersDonations()
    }

    fun getAllUsersDonations() {
        loader = createLoader(activity!!)
        showLoader(loader, "Downloading All Users Bookings from Firebase")
        val bookingsList = ArrayList<ValetModel>()
        app.database.child("bookings")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    info("Firebase Booking error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    hideLoader(loader)
                    val children = snapshot.children
                    children.forEach {
                        val booking = it.
                        getValue<ValetModel>(ValetModel::class.java)

                        bookingsList.add(booking!!)
                        root.recyclerView.adapter =
                            ValetingAdapter(bookingsList, this@ListAllFragment, true)
                        root.recyclerView.adapter?.notifyDataSetChanged()
                        checkSwipeRefresh()

                        app.database.child("bookings").removeEventListener(this)
                    }
                }
            })
    }
}
