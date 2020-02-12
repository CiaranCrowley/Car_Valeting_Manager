package ie.wit.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ie.wit.R
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.card_valet.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity() {

    var valet = ValetModel()
    var edit = false
    lateinit var app : ValetApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        app = application as ValetApp

        if(intent.hasExtra("booking_edit")){
            edit = true
            valet = intent.extras!!.getParcelable<ValetModel>("booking_edit")!!
            carBrandReport.text = valet.carBrand
            carModelReport.text = valet.carModel
            numberPlate.setText(valet.numberPlate)
            showDate.text = valet.date
            btnAddCar.text = "Save Booking"
        }

        btnAddCar.setOnClickListener{
            valet.carBrand = carBrand.text.toString()
            valet.carModel = carModel.text.toString()
            valet.numberPlate = numberPlate.text.toString()
            valet.date = showDate.text.toString()
            if(valet.carBrand.isEmpty()){
                toast("Please enter a car")
            }else {
                if (edit) {
                    app.valets.update(valet.copy())
                } else {
                    app.valets.create(valet.copy())
                }
            }
            toast("Add button Pressed: $carBrand")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        //Date Picker (https://stackoverflow.com/questions/45842167/how-to-use-datepickerdialog-in-kotlin#45844018)
        var cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd.MM.yyy"
            val sdf = SimpleDateFormat(format, Locale.UK)
            showDate.text = sdf.format(cal.time)
        }

        //Show Date Picker
        btnGoCalendar.setOnClickListener{
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu_booking, menu)
        if (edit && menu != null) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.valets.delete(valet)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
