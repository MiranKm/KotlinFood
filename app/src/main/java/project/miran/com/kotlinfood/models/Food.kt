package project.miran.com.kotlinfood.models
import com.google.gson.annotations.SerializedName


data class Food(
    val count: Int,
    val recipes: List<Recipe>
)
