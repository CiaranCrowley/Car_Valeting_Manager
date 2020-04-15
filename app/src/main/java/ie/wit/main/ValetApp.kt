package ie.wit.main

import android.app.Application
import android.util.Log
//import ie.wit.models.ValetJSONStore
import ie.wit.models.ValetMemStore
import ie.wit.models.ValetStore

class ValetApp : Application() {

    lateinit var valets: ValetStore

    override fun onCreate() {
        super.onCreate()
        //valets = ValetJSONStore(applicationContext)
        valets = ValetMemStore()
        Log.v("Valet","Valet App started")
    }
}