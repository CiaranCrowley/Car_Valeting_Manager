package ie.wit.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import ie.wit.R
//import ie.wit.car_valeting_manager.R
import ie.wit.fragments.ValetFragment
import ie.wit.fragments.ValetSavedFragment
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.home.*
import org.jetbrains.anko.toast


class Home : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var ft: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        setSupportActionBar(toolbar)

/*//        val calendarView = findViewById<CalendarView>(R.id.calendarView)
//        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
//            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
//            Toast.makeText(this@Home, msg, Toast.LENGTH_SHORT).show()
//        }*/


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action",
                Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }

        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        ft = supportFragmentManager.beginTransaction()

        val fragment = ValetFragment.newInstance()
        ft.replace(R.id.homeFrame, fragment)
        ft.commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_donate -> navigateTo(ValetFragment.newInstance())
            R.id.nav_report -> navigateTo(ValetSavedFragment.newInstance())

            else -> toast("You Selected Something Else")
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_donate -> toast("You Selected Donate")
            R.id.action_report -> toast("You Selected Report")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrame, fragment)
            .addToBackStack(null)
            .commit()
    }
}