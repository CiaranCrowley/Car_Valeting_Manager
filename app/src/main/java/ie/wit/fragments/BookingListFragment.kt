package ie.wit.fragments


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ie.wit.R
/*
import ie.wit.adapters.ValetListener
*/
import ie.wit.adapters.ValetingAdapter
import ie.wit.api.ValetWrapper
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import ie.wit.utils.*
import kotlinx.android.synthetic.main.fragment_booking_list.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.act
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingListFragment : Fragment(), AnkoLogger, Callback<List<ValetModel>> {

    lateinit var app: ValetApp
    lateinit var loader : AlertDialog
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
        root.recyclerView.adapter = ValetingAdapter(app.valets)
        loader = createLoader(activity!!)
        setSwipeRefresh()

        val swipeDeleteHandler = object : SwipeToDeleteCallback(activity!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = root.recyclerView.adapter as ValetingAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                deleteBooking(viewHolder.itemView.tag as String)
            }
        }
        val itemTouchDeleteHelper = ItemTouchHelper(swipeDeleteHandler)
        itemTouchDeleteHelper.attachToRecyclerView(root.recyclerView)

        //loadBookings()


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
                getAllBookings()
            }
        })
    }

    fun checkSwipeRefresh() {
        if (root.swiperefresh.isRefreshing) root.swiperefresh.isRefreshing = false
    }

    override fun onFailure(call: Call<List<ValetModel>>, t: Throwable) {
        info("Retrofit Error : $t.message")
        serviceUnavailableMessage(activity!!)
        checkSwipeRefresh()
        hideLoader(loader)
    }

    override fun onResponse(call: Call<List<ValetModel>>,
                            response: Response<List<ValetModel>>
    ) {
        serviceAvailableMessage(activity!!)
        info("Retrofit JSON = ${response.body()}")
        app.valets = response.body() as ArrayList<ValetModel>
        root.recyclerView.adapter = ValetingAdapter(app.valets)
        root.recyclerView.adapter?.notifyDataSetChanged()
        checkSwipeRefresh()
        hideLoader(loader)
    }

    fun getAllBookings() {
        showLoader(loader, "Downloading the Donations List")
        var callGetAll = app.valetService.getall()
        callGetAll.enqueue(this)
    }

    fun deleteBooking(id: String){
        showLoader(loader, "Deleting Booking $id")
        val callDelete = app.valetService.delete(id)
        callDelete.enqueue(object : Callback<ValetWrapper>{
            override fun onFailure(call: Call<ValetWrapper>, t: Throwable) {
                info("Retrofit Error : $t.message")
                serviceUnavailableMessage(activity!!)
                hideLoader(loader)
            }

            override fun onResponse(call: Call<ValetWrapper>, response: Response<ValetWrapper>) {
                hideLoader(loader)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getAllBookings()
    }
}
