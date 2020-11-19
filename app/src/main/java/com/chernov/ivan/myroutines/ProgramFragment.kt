package com.chernov.ivan.myroutines

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
import com.chernov.ivan.myroutines.databinding.FragmentProgramListBinding
import com.chernov.ivan.myroutines.util.ProgramRecyclerViewAdapter
import com.chernov.ivan.myroutines.model.ProgramEntity
import com.chernov.ivan.myroutines.util.NEW_ENTITY_ID
import com.chernov.ivan.myroutines.view_model.ProgramFragmentViewModel


class ProgramFragment : Fragment(), ProgramRecyclerViewAdapter.ListItemListener {

    private lateinit var viewModel: ProgramFragmentViewModel
    private lateinit var binding: FragmentProgramListBinding
    private lateinit var adapter: ProgramRecyclerViewAdapter

    // TODO: 02.11.2020 Customize parameters

    private var listener: OnListFragmentInteractionListener_program? = null

//    private var columnCount = 1

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        arguments?.let {
//            columnCount = it.getInt(ARG_COLUMN_COUNT)
//        }
//    }

//    companion object {
//
//        // TODO: Customize parameter argument names
//        const val ARG_COLUMN_COUNT = "column-count"
//
//        // TODO: Customize parameter initialization
//        @JvmStatic
//        fun newInstance(columnCount: Int) =
//            ProgramFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
//            }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_program_list, container, false)
//
//        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//                adapter =
//                    ProgramRecyclerViewAdapter(
//                        DummyContent.PROGRAMS_LIST,
//                        listener
//                    )
//            }
//        }
//        return view

        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        // TODO: 15.11.2020 change title of Program to day score
        requireActivity().title = getString(R.string.app_name)

        binding = FragmentProgramListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ProgramFragmentViewModel::class.java)

        with(binding.programList) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)

//            // TODO: 14.11.2020 to change with Room/LD
//            layoutManager = when {
//                columnCount <= 1 -> LinearLayoutManager(context)
//                else -> GridLayoutManager(context, columnCount)
//            }
//            adapter =
//                ProgramRecyclerViewAdapter(
//                    DummyContent.PROGRAMS_LIST,
//                    listener
//                )
        }

        viewModel.programsList?.observe(viewLifecycleOwner, Observer {
            Log.i("noteLogging", it.toString())
            //adapter = NotesListAdapter(it, this@MainFragment)
            if (it.isEmpty())
            viewModel.addSampleData()

            adapter = ProgramRecyclerViewAdapter(it, listener)
            binding.programList.adapter = adapter
            binding.programList.layoutManager = LinearLayoutManager(activity)

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
        if (context is OnListFragmentInteractionListener_program) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener_program")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener_program {
        // TODO: Update argument type and name
        fun onListFragmentInteraction_program(item: ProgramEntity?)
    }



    override fun editNote(noteId: Int) {
        // TODO: 14.11.2020 ???
//        Log.i(TAG, "onItemClick: received note id $noteId")
//        val action = MainFragmentDirections.actionEditNote(noteId)
//        findNavController().navigate(action)
    }

    override fun onItemSelectionChanged() {
        requireActivity().invalidateOptionsMenu()
        // TODO: 14.11.2020 ???
    }
}
