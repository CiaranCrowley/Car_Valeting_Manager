package ie.wit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.wit.R
import ie.wit.models.ValetModel
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.card_valet.view.*

interface ValetingListener{
    fun onValetClick(valet: ValetModel)
}

class ValetingAdapter constructor(var valets: ArrayList<ValetModel>,
                                  private val listener: ValetingListener, listall : Boolean)
    : RecyclerView.Adapter<ValetingAdapter.MainHolder>() {

    val listAll = listall

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
        val valet = valets[holder.adapterPosition]
        holder.bind(valet, listener, listAll)
    }

    override fun getItemCount(): Int = valets.size

    fun removeAt(position: Int){
        valets.removeAt(position)
        notifyItemRemoved(position)
    }

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(valet: ValetModel, listener: ValetingListener, listAll: Boolean) {
            itemView.tag = valet
            itemView.carBrandDisplay.text = valet.brand
            itemView.carModelDisplay.text = valet.model
            itemView.licensePlateDisplay.text = valet.numberPlate
            itemView.dateShown.text = valet.date

            if(!listAll)
                itemView.setOnClickListener { listener.onValetClick(valet) }

            if(!valet.profilepic.isEmpty()){
                Picasso.get().load(valet.profilepic.toUri())
                    //.resize(180, 180)
                    .transform(CropCircleTransformation())
                    .into(itemView.imageIcon)
            }
            else
                itemView.imageIcon.setImageResource(R.mipmap.ic_launcher_darth_maul_foreground)
        }
    }
}
