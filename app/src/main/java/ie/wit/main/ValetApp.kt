package ie.wit.main

import android.app.Application
import ie.wit.api.ValetService
import ie.wit.models.ValetModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ValetApp : Application(), AnkoLogger {

    lateinit var valetService: ValetService
    var valets = ArrayList<ValetModel>()

    override fun onCreate(){
        super.onCreate()
        info("Valet App Started")
        valetService = ValetService.create()
        info("Valet Service Created")
    }
}