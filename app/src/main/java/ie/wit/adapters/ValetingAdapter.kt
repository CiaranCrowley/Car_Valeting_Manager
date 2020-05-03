package ie.wit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.squareup.picasso.Picasso
import ie.wit.R
import ie.wit.fragments.ListAllFragment
import ie.wit.models.ValetModel
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.card_valet.view.*
import kotlinx.android.synthetic.main.fragment_booking.view.*

interface ValetingListener{
    fun onValetClick(valet: ValetModel)
}

/*class ValetingAdapter constructor(var valets: ArrayList<ValetModel>,
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
            if(valet.isfavourite) itemView.imagefavourite.setImageResource(android.R.drawable.star_big_on)

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
}*/
class ValetingAdapter(options: FirebaseRecyclerOptions<ValetModel>,
                      private val listener: ValetingListener?)
    : FirebaseRecyclerAdapter<ValetModel,
        ValetingAdapter.ValetViewHolder>(options) {

    class ValetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(valet: ValetModel, listener: ValetingListener) {
            with(valet) {
                itemView.tag = valet
                itemView.carBrandDisplay.text = valet.brand
                itemView.carModelDisplay.text = valet.model
                itemView.licensePlateDisplay.text = valet.numberPlate
                itemView.dateShown.text = valet.date
                if(valet.isfavourite) itemView.imagefavourite.setImageResource(android.R.drawable.star_big_on)

                if(listener is ListAllFragment)
                    ; // Do Nothing, Don't Allow 'Clickable' Rows
                else
                    itemView.setOnClickListener { listener.onValetClick(valet) }

                if(valet.isfavourite) itemView.imagefavourite.setImageResource(android.R.drawable.star_big_on)

                if(!valet.profilepic.isEmpty()) {
                    Picasso.get().load(valet.profilepic.toUri())
                        //.resizevalet
                        .transform(CropCircleTransformation())
                        .into(itemView.imageIcon)
                }
                else
                    itemView.imageIcon.setImageResource(R.mipmap.ic_launcher_darthvadar)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValetViewHolder {

        return ValetViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_valet, parent, false))
    }

    override fun onBindViewHolder(holder: ValetViewHolder, position: Int, model: ValetModel) {
        holder.bind(model,listener!!)
    }

    override fun onDataChanged() {
        // Called each time there is a new data snapshot. You may want to use this method
        // to hide a loading spinner or check for the "no documents" state and update your UI.
        // ...
    }
}