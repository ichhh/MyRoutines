package com.chernov.ivan.myroutines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DetailedFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedFragment()
    }

    private lateinit var viewModel: DetailedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detailed_fragment, container, false)
        val tv = view.findViewById<TextView>(R.id.tv_detail)
        tv.text = "${arguments?.getString("text1")} --- ${arguments?.getString("text2")}"
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
