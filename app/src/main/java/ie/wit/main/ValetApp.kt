package ie.wit.main

import android.app.Application
import ie.wit.models.ValetMemStore
import ie.wit.models.ValetStore

class ValetApp : Application() {

    //lateinit var valet: ValetStore
    val valet = ValetMemStore()

    override fun onCreate() {
        super.onCreate()
        //valet = ValetMemStore()
        //Log.v("Valet","Valet App started")
    }
}