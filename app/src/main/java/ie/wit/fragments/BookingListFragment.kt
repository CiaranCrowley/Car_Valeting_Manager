package ie.wit.fragments


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ie.wit.R
/*
import ie.wit.adapters.ValetListener
*/
import ie.wit.adapters.ValetingAdapter
import ie.wit.main.ValetApp
import ie.wit.models.ValetMemStore
import ie.wit.models.ValetModel
import ie.wit.utils.*
import kotlinx.android.synthetic.main.fragment_booking_list.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingListFragment : Fragment(), AnkoLogger, Callback<List<ValetModel>>/*, ValetListener*/ {

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
        loader = createLoader(activity!!)

        root.recyclerView.layoutManager = LinearLayoutManager(activity)
        root.recyclerView.adapter = ValetingAdapter(app.valetStore.findAll())

        setSwipeRefresh()

        //loadBookings()


        return root
    }

    /*private fun setButtonListener(layout: View){

    }*/

    companion object {
        @JvmStatic
        fun newInstance() =
            BookingListFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    fun setSwipeRefresh(){
        root.swipe_refresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh(){
                root.swipe_refresh.isRefreshing = true
                getAllBookings()
            }
        })
    }

    fun checkSwipeRefresh(){
        if(root.swipe_refresh.isRefreshing) root.swipe_refresh.isRefreshing = false
    }

    override fun onFailure(call: Call<List<ValetModel>>, t: Throwable) {
        info("Retrofit Error : $t.message")
        serviceUnavailableMessage(activity!!)
        checkSwipeRefresh()
        hideLoader(loader)
    }

    override fun onResponse(call: Call<List<ValetModel>>, response: Response<List<ValetModel>>) {
        serviceAvailableMessage(activity!!)
        info("Retrofit JSON = ${response.body()}")
        app.valetStore.valets = response.body() as ArrayList<ValetModel>
        root.recyclerView.adapter = ValetingAdapter(app.valetStore.findAll())
        root.recyclerView.adapter?.notifyDataSetChanged()
        checkSwipeRefresh()
        hideLoader(loader)
    }

    fun getAllBookings(){
        showLoader(loader, "Downloading the bookings list")
        var callGetAll = app.valetService.getall()
        callGetAll.enqueue(this)
    }

    override fun onResume() {
        super.onResume()
        getAllBookings()
    }

    /*override fun onValetClick(valet: ValetModel) {
        startActivityForResult(intentFor<BookingFragment>().putExtra("booking_edit", valet), 0)

    }*/

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //loadBookings()
        super.onActivityResult(requestCode, resultCode, data)
    }*/

    /*private fun loadBookings(){
        showBookings(app.valets.findAll())
    }

    private fun showBookings(bookings: List<ValetModel>){
        recyclerView.adapter = ValetingAdapter(bookings, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }*/
}
