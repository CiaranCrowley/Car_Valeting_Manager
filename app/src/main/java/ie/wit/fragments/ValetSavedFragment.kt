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

        root.recyclerView.layoutManager = LinearLayoutManager(activity)
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

