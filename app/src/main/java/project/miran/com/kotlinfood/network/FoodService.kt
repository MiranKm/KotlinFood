package project.miran.com.kotlinfood.network

import project.miran.com.kotlinfood.models.Food
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("search?key=3fdba3d2309da661bd3f278b40839766")
    fun getFood(@Query("q") search:String):Call<Food>
}