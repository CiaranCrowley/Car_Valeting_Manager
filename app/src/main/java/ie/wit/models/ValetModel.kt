package ie.wit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ValetModel(var id: Long = 0,
                      var brand: String = "",
                      var model: String = "",
                      var numberPlate: String = "",
                      /*var serviceType: String = "",*/
                      var date: String = "") : Parcelable