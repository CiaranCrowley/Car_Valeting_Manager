package ie.wit.fragments


import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import ie.wit.R
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.fragment_booking.*
import kotlinx.android.synthetic.main.fragment_booking.view.*
import org.jetbrains.anko.support.v4.toast
import java.util.*

class BookingFragment : Fragment() {

    var valet = ValetModel()
    var edit = false
    lateinit var app: ValetApp
    lateinit var ft: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_booking, container, false)
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

        val dpd = DatePickerDialog(this!!.activity!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

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
                    app.valetStore.create(valet.copy())
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
}
