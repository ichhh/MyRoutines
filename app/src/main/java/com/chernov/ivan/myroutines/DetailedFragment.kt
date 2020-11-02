package com.chernov.ivan.myroutines

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.detailed_fragment.*


class DetailedFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedFragment()
    }

    private lateinit var viewModel: DetailedViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detailed_fragment, container, false)
        val tv = view.findViewById<TextView>(R.id.tv_detail)

        arguments?.let {
            val args = DetailedFragmentArgs.fromBundle(it)
            tv.text = "${args.test1}  ${args.text2}"
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailedViewModel::class.java)
//        viewModel = ViewModelProviders.of(this).get(DetailedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
