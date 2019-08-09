package project.miran.com.kotlinfood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.miran.com.kotlinfood.R
import project.miran.com.kotlinfood.models.Recipe


class FoodRvAdapter(private val context: Context) :
    RecyclerView.Adapter<FoodRvAdapter.ViewHolder>() {


    private var recipeList: ArrayList<Recipe>
    private var lastPosition = -1

    init {
        recipeList = arrayListOf()
    }

    constructor(list: ArrayList<Recipe>, context: Context) : this(context) {
        this.recipeList = list
    }

    fun addItem(items: ArrayList<Recipe>?) {
        items?.let {
            recipeList = it
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(project.miran.com.kotlinfood.R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(recipeList)
        setAnimation(holder.itemView, position);

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(project.miran.com.kotlinfood.R.id.title)
        var image: ImageView = itemView.findViewById(project.miran.com.kotlinfood.R.id.image)

        fun setData(recipeList: List<Recipe>) {
            title.text = recipeList[adapterPosition].title
            Glide.with(itemView).load(recipeList[adapterPosition].imageUrl).into(image)
        }

    }


    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.insert_anim)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}