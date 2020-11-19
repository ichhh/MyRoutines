package com.chernov.ivan.myroutines

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.detailed_fragment.*


class EditorFragment : Fragment() {

//    private lateinit var viewModel: EditorViewModel
//    private lateinit var binding: EditorFragmentBinding
//
//
//    @SuppressLint("SetTextI18n")
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.editor_fragment, container, false)
//        val tv = view.findViewById<TextView>(R.id.tv_detail)
//
//        arguments?.let {
//            val args = EditorFragmentArgs.fromBundle(it)
//            tv.text = "${args.test1}  ${args.text2}"
//        }
//
//        return view
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
//        // TODO: Use the ViewModel
//    }


    private lateinit var viewModel: EditorViewModel
    private val args: EditorFragmentArgs by navArgs()
    private lateinit var binding: EditorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_check)
        }
        setHasOptionsMenu(true)

        requireActivity().title =
            if (args.itemId == NEW_ENTITY_ID) {
                getString(R.string.new_item)
            } else {
                getString(R.string.edit_item)
            }

        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)

        binding = EditorFragmentBinding.inflate(inflater, container, false)
        binding.etName.setText("")

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )

        viewModel.currentItem.observe(viewLifecycleOwner, Observer {
            val savedString = savedInstanceState?.getString(ITEM_TEXT_KEY)
            val cursorPosition = savedInstanceState?.getInt(CURSOR_POSITION_KEY) ?: 0
            binding.etName.setText(savedString ?: it.name)
            binding.etName.setSelection(cursorPosition)
        })
        viewModel.getItemById(args.itemId)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveAndReturn(): Boolean {

        val imm = requireActivity()
            .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

        viewModel.currentItem.value?.name = binding.etName.text.toString()
        viewModel.updateItem()

        findNavController().navigateUp()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        with(binding.etName) {
            outState.putString(ITEM_TEXT_KEY, text.toString())
            outState.putInt(CURSOR_POSITION_KEY, selectionStart)
        }
        super.onSaveInstanceState(outState)
    }
}
