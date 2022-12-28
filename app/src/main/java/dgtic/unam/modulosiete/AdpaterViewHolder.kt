package dgtic.unam.modulosiete

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import dgtic.unam.modulosiete.R

//Holder para ppintar datos
//Adapter para adaptar datos al recicler view
class AdapaterViewHolder(private val context: Context, private val data: List<Data>): Adapter<AdapaterViewHolder.ViewHolder>() {

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var name: TextView
        var description: TextView
        var image: ImageView
        var constraint:ConstraintLayout

        init {
            name= view.findViewById(R.id.nameView)
            description=view.findViewById(R.id.descriptionView)
            image= view.findViewById(R.id.imageView)
            constraint=view.findViewById(R.id.dataConstraint)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = LayoutInflater.from(parent.context)
        val v=li.inflate(R.layout.item_data, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info=data[position]
        holder.name.text=info.name
        holder.description.text=info.description
        holder.image.setImageResource(R.drawable.logo)
        holder.constraint.setOnClickListener{
            Toast.makeText(context, "Item" + data[position].name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}