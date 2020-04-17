package ie.wit.fragments


import androidx.appcompat.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ie.wit.R
import ie.wit.api.ValetWrapper
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import ie.wit.utils.*
import kotlinx.android.synthetic.main.fragment_booking.*
import kotlinx.android.synthetic.main.fragment_booking.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

@Suppress("NAME_SHADOWING")
class BookingFragment : Fragment(), AnkoLogger, Callback<List<ValetModel>> {

    var valet = ValetModel()
    var edit = false
    lateinit var app: ValetApp
    lateinit var loader : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_booking, container, false)
        loader = createLoader(activity!!)
        activity?.title = getString(R.string.action_book)

        setButtonListener(root)
        return root
    }

    private fun setButtonListener(layout: View){
        //Date Picker (https://stackoverflow.com/questions/45842167/how-to-use-datepickerdialog-in-kotlin#45844018)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this.activity!!, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            showDate.text = "$dayOfMonth/$monthOfYear/$year"
        }, year, month, day)

        layout.btnGoCalendar.setOnClickListener{
            dpd.show()
        }

        layout.btnAddCar.setOnClickListener{
            valet.brand= carBrand.text.toString()
            valet.model = carModel.text.toString()
            valet.numberPlate = numberPlate.text.toString()
            valet.date = showDate.text.toString()
            if(valet.brand.isEmpty()){
                toast("Please enter a car")
            }else {
                if (edit) {
                    //app.valets.update(valet.copy())
                } else {
                    //app.valetStore.create(valet.copy())
                    //addBooking(PUT SOMETHING IN HERE.. EG. "paymenttype = paymentmethod,amount = amount")
                }
            }

            print(carBrand)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BookingFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onResponse(call: Call<List<ValetModel>>,
                            response: Response<List<ValetModel>>) {
        serviceAvailableMessage(activity!!)
        info("Retrofit JSON = $response.raw()")
        app.valets = response.body() as ArrayList<ValetModel>
        updateUI()
        hideLoader(loader)
    }

    override fun onFailure(call: Call<List<ValetModel>>, t: Throwable) {
        info("Retrofit Error : $t.message")
        serviceUnavailableMessage(activity!!)
        hideLoader(loader)
    }

    fun updateUI() {
        /*totalDonated = app.donations.sumBy { it.amount }
        progressBar.progress = totalDonated
        totalSoFar.text = format("$ $totalDonated")*/
    }

    override fun onResume(){
        super.onResume()
        getAllBookings()
    }

    fun getAllBookings(){
        showLoader(loader, "Downloading Booking List")
        var callGetAll = app.valetService.findall(app.auth.currentUser?.email)
        callGetAll.enqueue(this)
    }

    fun addBooking(valet : ValetModel){
        showLoader(loader, "Adding booking to server...")
        var callAdd = app.valetService.post(app.auth.currentUser?.email, valet)
        callAdd.enqueue(object : Callback<ValetWrapper>{
            override fun onFailure(call: Call<ValetWrapper>, t: Throwable) {
                info("Retrofit Error : $t.message")
                serviceUnavailableMessage(activity!!)
                hideLoader(loader)
            }

            override fun onResponse(call: Call<ValetWrapper>, response: Response<ValetWrapper>) {
                val valetWrapper = response.body()
                info("Retrofit Wrapper : $valetWrapper")
                getAllBookings()
                updateUI()
                hideLoader(loader)
            }
        })
    }
}
