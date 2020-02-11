package ie.wit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.R
import ie.wit.activities.BookingActivity
import ie.wit.adapters.ValetListener
import ie.wit.adapters.ValetingAdapter
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.fragment_valet_saved.view.*
import org.jetbrains.anko.support.v4.intentFor


class DisplayBookingsFragment : Fragment(), ValetListener {

    lateinit var app: ValetApp
    /*lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_valet_saved, container, false)

        root.recyclerView.layoutManager = LinearLayoutManager(activity)
        root.recyclerView.adapter = ValetingAdapter(app.valetStore.findAll(), this)

        return root
    }

    /*override fun onValetClicked(valet : ValetModel){
        report_layout.setOnClickListener{
            startActivityForResult()
        }
    }*/

    companion object {
        @JvmStatic
        fun newInstance() =
            DisplayBookingsFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    override fun onValetClick(valet: ValetModel) {
        startActivityForResult(intentFor<BookingActivity>().putExtra("reminder_edit", valet), 0)
    }
}

