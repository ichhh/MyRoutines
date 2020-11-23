package com.chernov.ivan.myroutines.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.chernov.ivan.myroutines.R
import com.chernov.ivan.myroutines.databinding.ProgramEditDialogFragmentBinding
import com.chernov.ivan.myroutines.util.NEW_ENTITY_ID
import com.chernov.ivan.myroutines.view_model.ProgramEditDialogViewModel

class ProgramEditDialog : DialogFragment() {

    private lateinit var viewModel: ProgramEditDialogViewModel
    private var mHost: ProgramEditDialogListener? = null
    private val args: ProgramEditDialogArgs by navArgs()
    private lateinit var binding: ProgramEditDialogFragmentBinding

    interface ProgramEditDialogListener {
        fun onProgramEditDialogResult(programId: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create the custom layout using the LayoutInflater class
        val v =
            requireActivity().layoutInflater.inflate(R.layout.program_edit_dialog_fragment, null)

        // Build the dialog
        val title: String = if (args.programId == NEW_ENTITY_ID) {
            getString(R.string.new_program)
        } else {
            args.programName
        }

//        binding.etProgramName.hint = if (args.programId !== NEW_ENTITY_ID) {
//            "new name"}

//        DialogOlaBookingConfirmedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout. dialog_ola_booking_confirmed, null, false);
//        setContentView(binding.getRoot());
//        binding.setViewModel(new ViewModel(this, event.olaBooking));

        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(title)
            .setView(v)
//            .setView(binding.root)
            .setPositiveButton("OK") { dialog, which ->
                viewModel.insertProgram(
                    v?.findViewById<EditText>(R.id.et_ProgramName)?.text.toString(),
                    args.programId
                )
                //viewModel.insertProgram(binding.etProgramName.text.toString(), NEW_ENTITY_ID)
                //     .setNegativeButton("Cancel") { dialog, which -> Log.i(TAG, "Cancel clicked")
            }

        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mHost = activity as ProgramEditDialogListener
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProgramEditDialogViewModel::class.java)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
////        binding = ProgramEditDialogFragmentBinding.inflate(inflater,container,false)
////        binding = ProgramEditDialogFragmentBinding.inflate(LayoutInflater.from(, R.layout.program_edit_dialog_fragment, null, false)
////        return binding.root
////        return super.onCreateView(inflater, container, savedInstanceState)
//
//    }

}