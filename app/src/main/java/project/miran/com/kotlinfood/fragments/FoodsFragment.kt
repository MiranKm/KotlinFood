package project.miran.com.kotlinfood.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.miran.com.kotlinfood.R
import project.miran.com.kotlinfood.view_models.FoodsViewModel


class FoodsFragment : Fragment() {

    companion object {
        fun newInstance() = FoodsFragment()
    }

    private lateinit var viewModel: FoodsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.foods_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FoodsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
