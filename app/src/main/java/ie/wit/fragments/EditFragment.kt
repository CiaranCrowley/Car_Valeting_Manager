package ie.wit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import ie.wit.R
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import ie.wit.utils.createLoader

class EditFragment : Fragment() {

    lateinit var app: ValetApp
    lateinit var loader : AlertDialog
    lateinit var root: View
    var editDonation: ValetModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp

        arguments?.let {
            editDonation = it.getParcelable("editdonation")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_edit, container, false)
        activity?.title = getString(R.string.action_edit)
        loader = createLoader(activity!!)

        //todo This is where updating is done.  Make changes to suit my own stuff once firebase is implemented
        /*root.editAmount.setText(editDonation!!.amount.toString())
        root.editPaymenttype.setText(editDonation!!.paymenttype)
        root.editMessage.setText(editDonation!!.message)
        root.editUpvotes.setText(editDonation!!.upvotes.toString())*/

        return root
    }


    companion object {
        @JvmStatic
        fun newInstance(donation: ValetModel) =
            EditFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("editdonation",donation)
                }
            }
    }
}
