package com.example.MyRoutine2.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.MyRoutine2.NEW_ITEM_ID
import com.example.MyRoutine2.R
import com.example.MyRoutine2.databinding.ItemEditDialogBinding
import com.example.MyRoutine2.viewmodel.ItemEditViewModel
import java.text.DateFormat.getTimeInstance
import java.text.SimpleDateFormat


class ItemEditDialog : DialogFragment() {

    private lateinit var viewModel: ItemEditViewModel
//    private var mHost: ItemEditDialogListener? = null
    private val args: ItemEditDialogArgs by navArgs()
    private lateinit var binding: ItemEditDialogBinding


    interface ItemEditDialogListener {
        fun onItemEditDialogResult(ItemId: Int)
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create the custom layout using the LayoutInflater class
        val v =
            requireActivity().layoutInflater.inflate(R.layout.item_edit_dialog, null)

        binding = ItemEditDialogBinding.inflate(LayoutInflater.from(context))

        requireActivity().title =
            if (args.itemId == NEW_ITEM_ID) {
                getString(R.string.new_item)
            } else {
                getString(R.string.edit_item)
            }


        // Build the dialog
        binding.etItemName.setText(
            if (args.itemId == NEW_ITEM_ID) {
                getString(R.string.new_item)
            } else {
                args.itemName
            }
        )
        binding.etDuration.setText(args.itemDuration.toString())
        binding.cbPauseafter.isChecked = args.itemPauseAfter


//        binding.etItemName.hint = if (args.ItemId !== NEW_ENTITY_ID) {
//            "new name"}

//        DialogOlaBookingConfirmedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout. dialog_ola_booking_confirmed, null, false);
//        setContentView(binding.getRoot());
//        binding.setViewModel(new ViewModel(this, event.olaBooking));

        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("")
//            .setView(v)
            .setView(binding.root)
            .setPositiveButton("OK") { dialog, which ->
                viewModel.insertItem(
                    binding.etItemName.text.toString(),
                    args.itemId,
//                    getTimeInstance().parse(binding.etDuration.text.toString())?.time,
                    binding.etDuration.text.toString().toLong(),
                    binding.cbPauseafter.isChecked
                )
                //viewModel.insertItem(binding.etItemName.text.toString(), NEW_ENTITY_ID)
                //     .setNegativeButton("Cancel") { dialog, which -> Log.i(TAG, "Cancel clicked")
            }

        return builder.create()
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mHost = activity as ItemEditDialogListener
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ItemEditViewModel::class.java)
    }


//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        binding = ItemEditDialogFragmentBinding.inflate(inflater,container,false)
////        binding = ItemEditDialogFragmentBinding.inflate(LayoutInflater.from(, R.layout.Item_edit_dialog_fragment, null, false)
//        return binding.root
////        return super.onCreateView(inflater, container, savedInstanceState)
//
//    }

}