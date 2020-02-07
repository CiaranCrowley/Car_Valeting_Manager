package ie.wit.models

import android.util.Log

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ValetMemStore : ValetStore {

    val valets = ArrayList<ValetModel>()

    override fun findAll(): List<ValetModel> {
        return valets
    }

    override fun findById(id:Long) : ValetModel? {
        val foundDonation: ValetModel? = valets.find { it.id == id }
        return foundDonation
    }

    override fun create(valet: ValetModel) {
        valet.id = getId()
        valets.add(valet)
        logAll()
    }

    fun logAll() {
        Log.v("Donate","** Donations List **")
        valets.forEach { Log.v("Donate","${it}") }
    }
}