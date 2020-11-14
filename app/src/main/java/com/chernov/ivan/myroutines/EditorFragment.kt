package com.chernov.ivan.myroutines

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.detailed_fragment.*


class EditorFragment : Fragment() {

//    companion object {
//        fun newInstance() = DetailedFragment()
//    }

    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: EditorFragmentBinding


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.editor_fragment, container, false)
        val tv = view.findViewById<TextView>(R.id.tv_detail)

        arguments?.let {
            val args = EditorFragmentArgs.fromBundle(it)
            tv.text = "${args.test1}  ${args.text2}"
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
