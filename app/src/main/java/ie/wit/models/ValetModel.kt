package ie.wit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ValetModel(var id: Long = 0,
                      val date: String = "",
                      val paymentmethod: String = "N/A",
                      val amount: Int = 0) : Parcelable