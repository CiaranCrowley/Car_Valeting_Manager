package ie.wit.main

import android.app.Application
import android.app.IntentService
import android.util.Log
import ie.wit.api.ValetService
//import ie.wit.models.ValetJSONStore
import ie.wit.models.ValetMemStore
import ie.wit.models.ValetStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ValetApp : Application(), AnkoLogger {

    lateinit var valetStore: ValetStore
    lateinit var valetService: ValetService

    /*override fun onCreate() {
        super.onCreate()
        //valets = ValetJSONStore(applicationContext)
        valets = ValetMemStore()
        Log.v("Valet","Valet App started")
    }*/

    override fun onCreate(){
        super.onCreate()
        valetStore = ValetMemStore()
        info("Valet App Started")
        valetService = ValetService.create()
        info("Valet Service Created")
    }
}