package ie.wit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.R
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.card_valet.view.*

interface ValetListener{
    fun onValetClick(valet: ValetModel)
}

class ValetingAdapter constructor(private var valets: List<ValetModel>,
                                  private val listener: ValetListener)
    : RecyclerView.Adapter<ValetingAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_valet,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val valet = valets[holder.adapterPosition]
        holder.bind(valet, listener)
    }

    override fun getItemCount(): Int = valets.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(valet: ValetModel, listener: ValetListener) {
            itemView.carBrandDisplay.text = valet.brand
            //itemView.carBrandDisplay.setText(valet.brand)
            itemView.carModelDisplay.text = valet.model
            itemView.dateShown.text = valet.date
/*
            itemView.serviceType.text = valet.serviceType
*/
            itemView.setOnClickListener{listener.onValetClick(valet)}
        }
    }
}