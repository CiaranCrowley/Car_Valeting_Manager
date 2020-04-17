package ie.wit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.R
import ie.wit.models.ValetModel
import kotlinx.android.synthetic.main.card_valet.view.*

interface ValetingListener{
    fun onValetClick(valet: ValetModel)
}

class ValetingAdapter constructor(var valets: ArrayList<ValetModel>,
                                  private val listener: ValetingListener)
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

    fun removeAt(position: Int){
        valets.removeAt(position)
        notifyItemRemoved(position)
    }

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(valet: ValetModel, listener: ValetingListener) {
            itemView.tag = valet._id
            itemView.carBrandDisplay.text = valet.brand
            itemView.carModelDisplay.text = valet.model
            itemView.licensePlateDisplay.text = valet.numberPlate
            itemView.dateShown.text = valet.date
            itemView.setOnClickListener{listener.onValetClick(valet)}}
        }
    }
