package ie.wit.main

import android.app.Application
import android.util.Log
import ie.wit.models.ValetMemStore
import ie.wit.models.ValetStore

class ValetApp : Application() {

    lateinit var valetStore: ValetStore

    override fun onCreate() {
        super.onCreate()
        valetStore = ValetMemStore()
        Log.v("Donate","Donation App started")
    }
}