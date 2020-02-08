package ie.wit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ValetModel(var id: Long = 0,
                      var date: String = "N/A",
                      val paymentmethod: String = "N/A",
                      val amount: Int = 0) : Parcelable