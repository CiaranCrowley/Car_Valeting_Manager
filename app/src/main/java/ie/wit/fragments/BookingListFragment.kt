package ie.wit.fragments


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
import kotlinx.android.synthetic.main.fragment_booking_list.view.*

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
        root.recyclerView.adapter = ValetingAdapter(app.valets)

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
}
