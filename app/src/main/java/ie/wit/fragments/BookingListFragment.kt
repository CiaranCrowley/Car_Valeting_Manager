package ie.wit.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.R
/*
import ie.wit.adapters.ValetListener
*/
import ie.wit.adapters.ValetingAdapter
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.fragment_booking_list.*
import kotlinx.android.synthetic.main.fragment_booking_list.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor

class BookingListFragment : Fragment()/*, ValetListener*/ {

    lateinit var app: ValetApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_booking_list, container, false)

        root.recyclerView.layoutManager = LinearLayoutManager(activity)
        root.recyclerView.adapter = ValetingAdapter(app.valets.findAll())

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
