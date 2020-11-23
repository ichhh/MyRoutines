package com.chernov.ivan.myroutines.util


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chernov.ivan.myroutines.ProgramFragment
import com.chernov.ivan.myroutines.R
import com.chernov.ivan.myroutines.model.ProgramEntity
import kotlinx.android.synthetic.main.fragment_program.view.*


class ProgramRecyclerViewAdapter(
    private val mValues: List<ProgramEntity>,
    private val mListenerItem: ProgramFragment.OnListFragmentInteractionListener_program?,
    private val mListenerItemLongClick: ProgramFragment.OnListFragmentInteractionListener_program_longClick?
) : RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val program = v.tag as ProgramEntity
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            Log.d(TAG_D, "onListFragmentInteraction_program_Click_1:${program!!.id}")
            mListenerItem?.onListFragmentInteraction_program(program)
        }
    }

    private val mOnLongClickListener: View.OnLongClickListener

    init {
        mOnLongClickListener = View.OnLongClickListener { v ->
            val program = v.tag as ProgramEntity
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            Log.d(TAG_D, "onListFragmentInteraction_program_longClick_1:${program!!.id}")
            mListenerItemLongClick?.onListFragmentInteraction_program_longClick(program)
            return@OnLongClickListener true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_program, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = mValues[position]
        holder.mIdView.text = program.id.toString()
        holder.mNameView.text = program.name

        with(holder.mView) {
            tag = program
            setOnClickListener(mOnClickListener)
            setOnLongClickListener(mOnLongClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.program_id
        val mNameView: TextView = mView.program_name

        override fun toString(): String {
            return super.toString() + " '" + mNameView.text + "'"
        }
    }

    interface ListItemListener {
        fun editProgram(programId: Int, programName: String)
        fun onItemSelectionChanged()
    }
}

