package com.example.MyRoutine2.dialog

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.MyRoutine2.*
import com.example.MyRoutine2.databinding.ItemEditDialogBinding
import com.example.MyRoutine2.viewmodel.ItemEditDialogViewModel


class ItemEditDialog : DialogFragment() {

    private lateinit var viewModel: ItemEditDialogViewModel
    private val args: ItemEditDialogArgs by navArgs()
    private lateinit var binding: ItemEditDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemEditDialogViewModel::class.java)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create the custom layout using the LayoutInflater class
        val v =
            requireActivity().layoutInflater.inflate(R.layout.item_edit_dialog, null)

        binding = ItemEditDialogBinding.inflate(LayoutInflater.from(context))

        viewModel.currentItem.observe(this, Observer {

            binding.etItemName.setText(
                savedInstanceState?.getString(ITEM_NAME_KEY) ?: it.nameString
            )
            binding.etDuration.setText(
                savedInstanceState?.getString(ITEM_DURATION_KEY) ?: if (it.duration==null || it.duration!!.toInt()==0) "" else it.duration.toString()
            )
            binding.cbPauseafter.isChecked =
                savedInstanceState?.getBoolean(ITEM_DURATION_KEY) ?: it.pauseAfter

            binding.etItemName.setSelection(savedInstanceState?.getInt(CURSOR_POSITION_KEY) ?: 0)
        })
        viewModel.getItemById(args.itemId)


        requireActivity().title = if (args.itemId == NEW_ITEM_ID) {
                getString(R.string.new_item)
            } else {
                getString(R.string.edit_item)
            }


        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("")
//            .setView(v)
            .setView(binding.root)
            .setPositiveButton("OK") { dialog, which ->
                saveAndReturn()
//                viewModel.insertItem(
//                    binding.etItemName.text.toString(),
//                    args.itemId,
////                    getTimeInstance().parse(binding.etDuration.text.toString())?.time,
//                    binding.etDuration.text.toString().toLong(),
//                    binding.cbPauseafter.isChecked
//                )
            }
        //     .setNegativeButton("Cancel") { dialog, which -> Log.i(TAG, "Cancel clicked")


        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    private fun saveAndReturn(): Boolean {

        val imm = requireActivity()
            .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

        viewModel.currentItem.value?.nameString = binding.etItemName.text.toString()
        viewModel.currentItem.value?.duration = if (binding.etDuration?.text.toString() =="") 0L else binding.etDuration?.text.toString().toLong()
        viewModel.currentItem.value?.pauseAfter = binding.cbPauseafter.isChecked

        viewModel.updateItem()

        findNavController().navigateUp()
        return true

    }
}

//onAttach
//onCreate
//onCreateDialog
//onCreateView
//onActivityCreated
//onStart
//onResume
