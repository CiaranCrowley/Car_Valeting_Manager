package ie.wit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.R
import ie.wit.adapters.ValetingAdapter
import ie.wit.main.ValetApp
import kotlinx.android.synthetic.main.fragment_valet_saved.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ValetSavedFragment : Fragment() {

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
        var root = inflater.inflate(R.layout.fragment_valet_saved, container, false)

        root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        root.recyclerView.adapter = ValetingAdapter(app.valetStore.findAll())

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ValetSavedFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}

