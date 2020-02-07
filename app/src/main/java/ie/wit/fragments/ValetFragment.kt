package ie.wit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ie.wit.R
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.fragment_valet.*
import kotlinx.android.synthetic.main.fragment_valet.view.*
import org.jetbrains.anko.toast

class ValetFragment : Fragment() {

    lateinit var app: ValetApp
    var totalDonated = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ValetApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*var cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd.MM.yyy"
            val sdf = SimpleDateFormat(format, Locale.UK)
            showDate.text = sdf.format(cal.time)
        }

        //Show Date Picker
        showCalendar.setOnClickListener{
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }*/

        val root = inflater.inflate(R.layout.fragment_valet, container, false)
        activity?.title = getString(R.string.action_donate)

        root.progressBar.max = 10000
        root.amountPicker.minValue = 1
        root.amountPicker.maxValue = 1000

        root.amountPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //Display the newly selected number to paymentAmount
            root.paymentAmount.setText("$newVal")
        }
        setButtonListener(root)

        root.showCalendar.setOnClickListener{
            calendarView
        }

        /*showCalendar.setOnClickListener{
            calendarView
        }*/

        return root
    }


    //https://www.youtube.com/watch?v=hHjFIG0TtA0&feature=youtu.be

    companion object {
        @JvmStatic
        fun newInstance() =
            ValetFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    fun setButtonListener( layout: View) {
        layout.donateButton.setOnClickListener {
            val amount = if (layout.paymentAmount.text.isNotEmpty())
                layout.paymentAmount.text.toString().toInt() else layout.amountPicker.value
            if(totalDonated >= layout.progressBar.max)
                activity?.toast("Donate Amount Exceeded!")
            else {
                val paymentmethod = if(layout.paymentMethod.checkedRadioButtonId == R.id.Direct) "Direct" else "Paypal"
                totalDonated += amount
                layout.totalSoFar.text = "$$totalDonated"
                layout.progressBar.progress = totalDonated
                app.valetStore.create(ValetModel(paymentmethod = paymentmethod,amount = amount))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        totalDonated = app.valetStore.findAll().sumBy { it.amount }
        progressBar.progress = totalDonated
        totalSoFar.text = "$$totalDonated"
    }
}