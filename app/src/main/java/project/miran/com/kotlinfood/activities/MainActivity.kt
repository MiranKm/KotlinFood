package project.miran.com.kotlinfood.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import project.miran.com.kotlinfood.R
import com.google.gson.annotations.SerializedName


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.container)
        bottom_nav.setupWithNavController(navController)

//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
