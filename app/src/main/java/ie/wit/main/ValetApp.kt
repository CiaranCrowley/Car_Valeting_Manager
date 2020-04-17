package ie.wit.main

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import ie.wit.api.ValetService
import ie.wit.models.ValetModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ValetApp : Application(), AnkoLogger {

    lateinit var valetService: ValetService
    var valets = ArrayList<ValetModel>()

    // [START declare_auth]
    lateinit var auth: FirebaseAuth
    // [END declare_auth]

    override fun onCreate(){
        super.onCreate()
        info("Valet App Started")
        valetService = ValetService.create()
        info("Valet Service Created")
    }
}