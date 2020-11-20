package com.chernov.ivan.myroutines.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.chernov.ivan.myroutines.R
import com.chernov.ivan.myroutines.util.TAG
import com.chernov.ivan.myroutines.view_model.ProgramEditDialogViewModel

class ProgramEditDialog : DialogFragment() {

    private lateinit var viewModel: ProgramEditDialogViewModel
    private var mHost: ProgramEditDialogListener? = null

    interface ProgramEditDialogListener {
        fun onProgramEditDialogResult(programId: Int)

    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.program_edit_dialog_fragment, container, false)
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())


        // Create the custom layout using the LayoutInflater class
        val inflater = requireActivity().layoutInflater
        val v = inflater.inflate(R.layout.program_edit_dialog_fragment, null)

        // Build the dialog
        builder.setTitle("Program name")
            .setPositiveButton("OK") { dialog, which -> Log.i(TAG, "OK Clicked") }
            //     .setNegativeButton("Cancel") { dialog, which -> Log.i(TAG, "Cancel clicked") }
            .setView(v)

        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mHost = activity as ProgramEditDialogListener
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProgramEditDialogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}