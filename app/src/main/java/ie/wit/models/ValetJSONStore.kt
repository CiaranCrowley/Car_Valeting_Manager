package ie.wit.models


/*
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import ie.wit.helpers.*
import java.util.*


val JSON_FILE = "bookings.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object: TypeToken<ArrayList<ValetModel>>(){}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ValetJSONStore: ValetStore, AnkoLogger{
    val context: Context
    var bookings = mutableListOf<ValetModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<ValetModel> {
        return bookings
    }


    override fun create(valet: ValetModel) {
        valet.id = generateRandomId()
        bookings.add(valet)
        serialize()
    }

    override fun update(valet: ValetModel) {
        val bookingsList = findAll() as ArrayList<ValetModel>
        var foundBooking: ValetModel? = bookingsList.find{p -> p.id == valet.id}
        if(foundBooking != null){
            foundBooking.brand = valet.brand
            foundBooking.model = valet.model
            foundBooking.numberPlate = valet.numberPlate
            foundBooking.date = valet.date
        }
        serialize()
    }

    override fun delete(valet: ValetModel) {
        bookings.remove(valet)
        serialize()
    }

    private fun serialize(){
        val jsonString = gsonBuilder.toJson(bookings, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        bookings = Gson().fromJson(jsonString, listType)
    }

}*/
