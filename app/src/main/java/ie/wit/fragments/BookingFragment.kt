package ie.wit.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ie.wit.R
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.fragment_valet.*
import kotlinx.android.synthetic.main.fragment_valet.view.*
import java.text.SimpleDateFormat
import java.util.*


class BookingFragment : Fragment() {

    lateinit var app: ValetApp
    /*var edit = false
    var valet = ValetModel()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp

        /*if(intent.hasExtra("valet_edit")){
            edit = true
            valet = intent.extras.getParcelable<ValetModel>("valet_edit")
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_valet, container, false)
        activity?.title = getString(R.string.action_donate)

        setButtonListener(root)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BookingFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    fun setButtonListener( layout: View) {

        //Date Picker  (https://stackoverflow.com/questions/45842167/how-to-use-datepickerdialog-in-kotlin#45844018)
        var cal = Calendar.getInstance()

        layout.btnGoCalendar.setOnClickListener {
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "dd/MM/yyy"
                val sdf = SimpleDateFormat(format, Locale.UK)
                showDate.text = sdf.format(cal.time)
            }

            DatePickerDialog(activity!!, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        layout.btnAddCar.setOnClickListener{
            val brand : String = carBrand.text.toString()
            val model : String = carModel.text.toString()
            val regNo : String = numberPlate.text.toString()
            val service = if(layout.serviceType.checkedRadioButtonId == R.id.premiumService) "Premium Service" else "Standard Service"
            val date : String = showDate.text.toString()
            app.valetStore.create(ValetModel(carBrand = brand,carModel = model,
                numberPlate = regNo,
                serviceType = service,
                date = date
            ))

            /*ft = supportFragmentManager.beginTransaction()
            val fragment = ValetSavedFragment.newInstance()
            ft.replace(R.id.homeFrame, fragment)
            ft.commit()*/
        }
    }

    override fun onResume() {
        super.onResume()
    }
}