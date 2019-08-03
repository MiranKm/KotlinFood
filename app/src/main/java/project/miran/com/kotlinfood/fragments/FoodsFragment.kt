package fragments

import android.content.Context
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.foods_fragment.view.*
import project.miran.com.kotlinfood.R
import project.miran.com.kotlinfood.adapter.FoodRvAdapter
import project.miran.com.kotlinfood.models.Food
import project.miran.com.kotlinfood.network.ServiceCreator
import project.miran.com.kotlinfood.view_models.FoodsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodsFragment : Fragment(), View.OnClickListener, View.OnFocusChangeListener {


    private lateinit var layout: ConstraintLayout
    private lateinit var editQuery: AutoCompleteTextView
    private val constraintSetStart = ConstraintSet()
    private val constraintSetEnd = ConstraintSet()
    private lateinit var viewModel: FoodsViewModel
    private lateinit var searchBtn: MaterialButton


    companion object {
        fun newInstance() = FoodsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.foods_fragment, container, false)

        searchBtn = view.findViewById(R.id.search_btn)
        val recycler: RecyclerView = view.findViewById(R.id.recycler)
        recycler.setHasFixedSize(false)
        recycler.layoutManager = LinearLayoutManager(context)
        editQuery = view.findViewById(R.id.edit_query)
        editQuery.onFocusChangeListener = this
        layout = view.findViewById(R.id.layout)
        layout.setOnClickListener(this)
        constraintSetStart.clone(layout)
        constraintSetEnd.clone(context, R.layout.foods_fragment_alt)

        searchBtn.setOnClickListener {
            view.progress_bar.show()
            if (!editQuery.text.toString().trim().isEmpty()) {
                val apiCalls =
                    ServiceCreator.instance.getFood(editQuery.text.toString().trim()).enqueue(object : Callback<Food> {
                        override fun onFailure(call: Call<Food>, t: Throwable) {
                            Log.d("tag", "message ${t.message}")
                        }

                        override fun onResponse(call: Call<Food>, response: Response<Food>) {
                            view.progress_bar.hide()
                            editQuery.clearFocus()
                            val adapter = FoodRvAdapter(response.body()!!.recipes, context!!)
                            recycler.adapter = adapter

//                view.foods.text = response.body()!!.recipes[0].title
                        }

                    })
            } else Toast.makeText(context, "Can't be empty", Toast.LENGTH_SHORT).show()




        }

      



        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FoodsViewModel::class.java)
    }

    override fun onClick(v: View?) {
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (v!!.id == R.id.edit_query) {
            TransitionManager.beginDelayedTransition(layout)
            if (hasFocus) {
                constraintSetEnd.applyTo(layout)
            } else {
                val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.hideSoftInputFromWindow(v.windowToken, 0)
                constraintSetStart.applyTo(layout)
            }
        }
    }
}
