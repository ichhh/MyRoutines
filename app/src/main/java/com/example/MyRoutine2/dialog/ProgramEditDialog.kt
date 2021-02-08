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
import com.example.MyRoutine2.databinding.ProgramEditDialogFragmentBinding
import com.example.MyRoutine2.viewmodel.ProgramEditDialogViewModel


class ProgramEditDialog : DialogFragment() {

    private lateinit var viewModel: ProgramEditDialogViewModel
    private val args: ProgramEditDialogArgs by navArgs()
    private lateinit var binding: ProgramEditDialogFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProgramEditDialogViewModel::class.java)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create the custom layout using the LayoutInflater class
        val v =
            requireActivity().layoutInflater.inflate(R.layout.item_edit_dialog, null)

        binding = ProgramEditDialogFragmentBinding.inflate(LayoutInflater.from(context))

        viewModel.currentProgram.observe(this, Observer {

            binding.etProgramName.setText(
                savedInstanceState?.getString(ITEM_NAME_KEY) ?: it.nameString
            )


            binding.etProgramName.setSelection(savedInstanceState?.getInt(CURSOR_POSITION_KEY) ?: 0)
        })
        viewModel.getProgramById(args.programId)


        requireActivity().title = if (args.programId == NEW_ITEM_ID_INT) {
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
//                viewModel.insertProgram(
//                    binding.etProgramName.text.toString(),
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

        viewModel.currentProgram.value?.nameString = binding.etProgramName.text.toString()
//        viewModel.currentProgram.value?.duration = if (binding.etDuration?.text.toString() =="") 0L else binding.etDuration?.text.toString().toLong()
//        viewModel.currentProgram.value?.pauseAfter = binding.cbPauseafter.isChecked

        viewModel.updateProgram()

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
