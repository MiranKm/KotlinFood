package project.miran.com.kotlinfood.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.miran.com.kotlinfood.models.Food
import project.miran.com.kotlinfood.models.Recipe
import project.miran.com.kotlinfood.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsViewModel : ViewModel() {

    private val recipeList = MutableLiveData<ArrayList<Recipe>>()

    fun getData(query: String) {

        val apiCalls =
            ServiceCreator.instance.getFood(query).enqueue(object : Callback<Food> {
                override fun onFailure(call: Call<Food>, t: Throwable) {
                    Log.d("tag", "message ${t.message}")
                }

                override fun onResponse(call: Call<Food>, response: Response<Food>) {
                    response.body()?.recipes?.let {
                        Log.d("tag", "message ${it.size}")
                        recipeList.value = it

                    }
                }

            })
    }


    fun getRecipeList(): MutableLiveData<ArrayList<Recipe>> {
        return recipeList
    }
}
