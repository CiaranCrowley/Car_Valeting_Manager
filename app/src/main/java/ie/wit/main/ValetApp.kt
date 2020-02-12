package ie.wit.main

import android.app.Application
import ie.wit.models.ValetMemStore
import ie.wit.models.ValetStore

class ValetApp : Application() {

    //lateinit var valet: ValetStore
    val valets = ValetMemStore()

    override fun onCreate() {
        super.onCreate()
        //valets = ValetMemStore()
        //Log.v("Valet","Valet App started")
    }
}