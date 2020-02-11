package ie.wit.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import ie.wit.R
import ie.wit.fragments.BookingFragment
import ie.wit.fragments.DisplayBookingsFragment
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_valet.*
import kotlinx.android.synthetic.main.home.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener  {

    var valet = ValetModel()
    var edit = false
    //lateinit var app : ValetApp
    lateinit var ft: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        //app = application as ValetApp

        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        ft = supportFragmentManager.beginTransaction()

        val fragment = BookingFragment.newInstance()
        ft.replace(R.id.homeFrame, fragment)
        ft.commit()

        /*if(intent.hasExtra("reminder_edit")){
            edit = true
            valet = intent.extras.getParcelable<ValetModel>("reminder_edit")
            carBrand.setText(valet.carBrand)
            carModel.setText(valet.carModel)
            numberPlate.setText(valet.numberPlate)
            showDate.setText(valet.date)
        }*/

        /*var cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd/MM/yyy"
            val sdf = SimpleDateFormat(format, Locale.UK)
            showDate.text = sdf.format(cal.time)
        }

        btnGoCalendar.setOnClickListener{
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }*/
    }

    public fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrame, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //todo Change the donate  nav_donate & nav_report is in activity_home_drawer.xml
            R.id.nav_donate -> navigateTo(BookingFragment.newInstance())
            R.id.nav_report -> navigateTo(DisplayBookingsFragment.newInstance())

            else -> toast("You Selected Something Else")
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
