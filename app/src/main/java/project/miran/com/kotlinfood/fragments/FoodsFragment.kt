package project.miran.com.kotlinfood.fragments

import android.content.Context
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.foods_fragment.view.*
import project.miran.com.kotlinfood.R
import project.miran.com.kotlinfood.adapter.FoodRvAdapter
import project.miran.com.kotlinfood.view_models.FoodsViewModel


class FoodsFragment : Fragment(), View.OnClickListener, View.OnFocusChangeListener {


    private lateinit var layout: ConstraintLayout
    private lateinit var editQuery: AutoCompleteTextView
    private val constraintSetStart = ConstraintSet()
    private val constraintSetEnd = ConstraintSet()
    private lateinit var viewModel: FoodsViewModel
    private lateinit var searchBtn: MaterialButton
    private lateinit var adapter: FoodRvAdapter

    companion object {
        fun newInstance() = FoodsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(FoodsViewModel::class.java)

        val view = inflater.inflate(R.layout.foods_fragment, container, false)
        adapter = FoodRvAdapter(context!!)

        searchBtn = view.findViewById(R.id.search_btn)
        val recycler: RecyclerView = view.findViewById(R.id.recycler)
        recycler.setHasFixedSize(false)
        recycler.layoutManager = GridLayoutManager(context, 2)
        editQuery = view.findViewById(R.id.edit_query)
        editQuery.onFocusChangeListener = this
        layout = view.findViewById(R.id.layout)
        layout.setOnClickListener(this)
        constraintSetStart.clone(layout)
        constraintSetEnd.clone(context, R.layout.foods_fragment_alt)

        searchBtn.setOnClickListener {
            view!!.progress_bar.visibility = ProgressBar.VISIBLE
            viewModel.getData("pizza")
        }

        viewModel.getRecipeList().observe(this, Observer {
            adapter.addItem(it)
        })


        editQuery.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                view!!.progress_bar.visibility = ProgressBar.VISIBLE
                viewModel.getData("pizza")


                editQuery.clearFocus()

            }
            false
        }

        recycler.adapter = adapter

        return view;
    }

    override fun onResume() {
        super.onResume()

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
