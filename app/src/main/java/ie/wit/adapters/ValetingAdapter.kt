package ie.wit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.R
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.card_valet.view.*

class ValetingAdapter constructor(private var donations: List<ValetModel>)
    : RecyclerView.Adapter<ValetingAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_valet,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val valet = donations[holder.adapterPosition]
        holder.bind(valet)
    }

    override fun getItemCount(): Int = donations.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(donation: ValetModel) {
            itemView.paymentamount.text = donation.amount.toString()
            itemView.paymentmethod.text = donation.paymentmethod
            itemView.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}