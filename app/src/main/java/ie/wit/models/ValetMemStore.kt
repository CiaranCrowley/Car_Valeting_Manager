package ie.wit.models

import android.util.Log

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ValetMemStore : ValetStore {

    val donations = ArrayList<ValetModel>()

    override fun findAll(): List<ValetModel> {
        return donations
    }

    override fun findById(id:Long) : ValetModel? {
        val foundDonation: ValetModel? = donations.find { it.id == id }
        return foundDonation
    }

    override fun create(donation: ValetModel) {
        donation.id = getId()
        donations.add(donation)
        logAll()
    }

    fun logAll() {
        Log.v("Donate","** Donations List **")
        donations.forEach { Log.v("Donate","${it}") }
    }
}