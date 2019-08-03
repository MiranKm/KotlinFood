package project.miran.com.kotlinfood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.miran.com.kotlinfood.R
import project.miran.com.kotlinfood.models.Recipe

class FoodRvAdapter(val data: List<Recipe>, val context: Context) : RecyclerView.Adapter<FoodRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var image: ImageView = itemView.findViewById(R.id.image)

        fun setData(data: List<Recipe>) {
            title.text = data[adapterPosition].title
            Glide.with(itemView).load(data[adapterPosition].imageUrl).into(image)
        }
    }
}