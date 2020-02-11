package ie.wit.models

import android.util.Log
import org.jetbrains.anko.AnkoLogger

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ValetMemStore : ValetStore, AnkoLogger {

    val valets = ArrayList<ValetModel>()

    override fun findAll(): List<ValetModel> {
        return valets
    }

    override fun create(valet: ValetModel) {
        valets.add(valet)
        logAll()
    }

    override fun update(valet: ValetModel) {
        var foundBooking: ValetModel? = valets.find{p -> p.id == valet.id}
        if(foundBooking != null){
            foundBooking.carBrand = valet.carBrand
            foundBooking.carModel = valet.carModel
            foundBooking.numberPlate = valet.numberPlate
/*
            foundBooking.serviceType = valet.serviceType
*/
            foundBooking.date = valet.date
            logAll()
        }
    }

    override fun delete(valet: ValetModel) {
        valets.remove(valet)
    }

    fun logAll() {
        //todo these have to be changed
        valets.forEach { Log.v("Donate","${it}") }
    }
}