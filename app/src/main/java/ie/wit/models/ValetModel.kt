package ie.wit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ValetModel(var _id: String = "",
                      var brand: String = "",
                      var model: String = "",
                      var numberPlate: String = "",
                      var date: String = "",
                      var email: String = "joe@bloggs.com") : Parcelable


/*var serviceType: String = "",*/