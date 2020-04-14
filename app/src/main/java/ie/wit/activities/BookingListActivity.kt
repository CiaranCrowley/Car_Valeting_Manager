package ie.wit.activities

/*
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.R
import ie.wit.adapters.ValetListener
import ie.wit.adapters.ValetingAdapter
import ie.wit.main.ValetApp
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.activity_booking_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult


class BookingListActivity : AppCompatActivity(), ValetListener{

    lateinit var app: ValetApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_list)
        app = application as ValetApp

        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadBookings()

        */
/*enable action bar and set title
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)*//*

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<BookingActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onValetClick(valet: ValetModel) {
        startActivityForResult(intentFor<BookingActivity>().putExtra("booking_edit", valet), 0)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadBookings()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadBookings(){
        showBookings(app.valets.findAll())
    }

    fun showBookings(bookings: List<ValetModel>){
        recyclerView.adapter = ValetingAdapter(bookings, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }


}*/
