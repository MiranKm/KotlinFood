package project.miran.com.kotlinfood.models

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("f2f_url")
    val f2fUrl: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val publisher: String,
    @SerializedName("publisher_url")
    val publisherUrl: String,
    @SerializedName("recipe_id")
    val recipeId: String,
    @SerializedName("social_rank")
    val socialRank: Double,
    @SerializedName("source_url")
    val sourceUrl: String,
    val title: String
)