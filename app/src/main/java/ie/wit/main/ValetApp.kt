package ie.wit.main

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import ie.wit.models.ValetModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ValetApp : Application(), AnkoLogger {

    lateinit var database: DatabaseReference
    var valets = ArrayList<ValetModel>()

    // [START declare_auth]
    lateinit var auth: FirebaseAuth
    // [END declare_auth]

    override fun onCreate(){
        super.onCreate()
        info("Valet App Started")
        info("Valet Service Created")
    }
}