package project.miran.com.kotlinfood.fragments

import android.content.Context
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import project.miran.com.kotlinfood.view_models.FoodsViewModel


class FoodsFragment : Fragment(), View.OnClickListener, View.OnFocusChangeListener {
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (v!!.id == project.miran.com.kotlinfood.R.id.edit_query) {
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

    lateinit var layout: ConstraintLayout
    lateinit var editQuery: AutoCompleteTextView
    val constraintSetStart = ConstraintSet()
    val constraintSetEnd = ConstraintSet()
    var isLay: Boolean = false



    companion object {
        fun newInstance() = FoodsFragment()
    }

    private lateinit var viewModel: FoodsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(project.miran.com.kotlinfood.R.layout.foods_fragment, container, false)

        editQuery = view.findViewById(project.miran.com.kotlinfood.R.id.edit_query)
        editQuery.setOnFocusChangeListener(this)
        layout = view.findViewById(project.miran.com.kotlinfood.R.id.layout)
        layout.setOnClickListener(this)
        constraintSetStart.clone(layout)
        constraintSetEnd.clone(context, project.miran.com.kotlinfood.R.layout.foods_fragment_alt)

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FoodsViewModel::class.java)
    }

    override fun onClick(v: View?) {
    }

}
