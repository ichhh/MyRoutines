package com.chernov.ivan.myroutines.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chernov.ivan.myroutines.R
import com.chernov.ivan.myroutines.databinding.FragmentItemListBinding
import com.chernov.ivan.myroutines.model.ItemEntity
import com.chernov.ivan.myroutines.util.NEW_ENTITY_ID
import com.chernov.ivan.myroutines.view_model.ItemFragmentViewModel

class ItemFragment : Fragment(),ItemRecyclerViewAdapter.ListItemListener {

    private lateinit var viewModel: ItemFragmentViewModel
    private lateinit var binding: FragmentItemListBinding
    private lateinit var adapter: ItemRecyclerViewAdapter

    private var listener: OnListFragmentInteractionListener? = null

//    private var columnCount = 1
    private var  programID:Int? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        arguments?.let {
//            columnCount = it.getInt(ARG_COLUMN_COUNT)
//        }
//    }

//    companion object {
//
//
//        // TODO: Customize parameter argument names
//        const val ARG_COLUMN_COUNT = "idProgram" // TODO: 15.11.2020 to SaveArg
//
//        // TODO: Customize parameter initialization
//        @JvmStatic
//        fun newInstance(columnCount: Int) =
//            ItemFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
//            }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

//        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//
//                arguments?.let {
//                    val args = ItemFragmentArgs.fromBundle(it)
//                    programID = args.idProgram
//                }
//
//                adapter =
//                    programID.let { ItemRecyclerViewAdapter(
//                        DummyContent.getItemsOfProgram(programID!!),
//                        listener) }
//
//            }
//        }
//        return view

        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        // TODO: 15.11.2020 change title of Program to it's name
        requireActivity().title = getString(R.string.app_name)

        binding = FragmentItemListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ItemFragmentViewModel::class.java)

        with(binding.itemList) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)

        }

        viewModel.itemsList?.observe(viewLifecycleOwner, Observer {
            Log.i("noteLogging", it.toString())
            //adapter = NotesListAdapter(it, this@MainFragment)
            if (it.isEmpty())
                viewModel.addSampleData()

            adapter = ItemRecyclerViewAdapter(it, listener)
            binding.itemList.adapter = adapter
            binding.itemList.layoutManager = LinearLayoutManager(activity)

            // TODO: 15.11.2020 conf change handling?
//            val selectedPrograms =
//                savedInstanceState?.getParcelableArrayList<ProgramEntity>(SELECTED_ENTITY_KEY)
//            adapter.selectedNotes.addAll(selectedNotes ?: emptyList())
        })

        binding.fab.setOnClickListener {
            editNote(NEW_ENTITY_ID)
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: ItemEntity?)
    }



    override fun editNote(noteId: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemSelectionChanged() {
        TODO("Not yet implemented")
    }
}
