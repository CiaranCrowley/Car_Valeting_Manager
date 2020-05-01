package ie.wit.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ValetModel(
    var uid: String? = "",
    var brand: String = "N/A",
    var model: String = "N/A",
    var numberPlate: String = "N/A",
    var date: String = "N/A",
    var profilepic: String = "",
    var email: String? = "joe@bloggs.com") : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "brand" to brand,
            "model" to model,
            "numberPlate" to numberPlate,
            "date" to date,
            "profilepic" to profilepic,
            "email" to email
        )
    }
}

/*var serviceType: String = "",*/