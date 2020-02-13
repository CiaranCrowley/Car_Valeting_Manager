package ie.wit.main

import android.app.Application
import ie.wit.models.ValetJSONStore
import ie.wit.models.ValetStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ValetApp : Application(), AnkoLogger {

    lateinit var valets: ValetStore

    override fun onCreate() {
        super.onCreate()
        valets = ValetJSONStore(applicationContext)
        info("Booking started")
    }
}