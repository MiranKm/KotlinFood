package project.miran.com.kotlinfood.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    val instance: FoodService by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.food2fork.com/api/")
            .build()
         retrofit.create(FoodService::class.java)
    }

}