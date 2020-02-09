package ie.wit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ValetModel(var id: Long = 0,
                      var carBrand: String = "",
                      var carModel: String = "",
                      var numberPlate: String = "",
                      var serviceType: String = "",
                      var date: String = "") : Parcelable