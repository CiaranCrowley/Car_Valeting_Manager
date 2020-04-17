package ie.wit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import ie.wit.R
import ie.wit.api.ValetWrapper
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import ie.wit.utils.createLoader
import ie.wit.utils.hideLoader
import ie.wit.utils.serviceUnavailableMessage
import ie.wit.utils.showLoader
import kotlinx.android.synthetic.main.fragment_edit.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditFragment : Fragment(), Callback<ValetWrapper>, AnkoLogger {

    lateinit var app: ValetApp
    lateinit var loader : AlertDialog
    lateinit var root: View
    var editBooking: ValetModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp

        arguments?.let {
            editBooking = it.getParcelable("editdonation")
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

        root.editUpdateButton.setOnClickListener {
            showLoader(loader, "Updating Donation on Server...")
            updateBookingData()
            var callUpdate = app.valetService.put(app.auth.currentUser?.email,
                (editBooking as ValetModel)._id ,editBooking as ValetModel)
            callUpdate.enqueue(this)
        }

        return root
    }

    override fun onFailure(call: Call<ValetWrapper>, t: Throwable) {
        info("Retrofit Error : $t.message")
        serviceUnavailableMessage(activity!!)
        hideLoader(loader)
    }

    override fun onResponse(call: Call<ValetWrapper>, response: Response<ValetWrapper>) {
        hideLoader(loader)
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrame, BookingListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    fun updateBookingData(){
        //todo change the obvious parts of this
       /* editDonation!!.amount = root.editAmount.text.toString().toInt()
        editDonation!!.message = root.editMessage.text.toString()
        editDonation!!.upvotes = root.editUpvotes.text.toString().toInt()*/
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
