package com.chernov.ivan.myroutines.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.chernov.ivan.myroutines.R
import com.chernov.ivan.myroutines.model.Program
import kotlinx.android.synthetic.main.fragment_program.view.*

//import kotlinx.android.synthetic.main.fragment_item.view.*


class ProgramRecyclerViewAdapter(
    private val mValues: List<Program>,
    private val mListenerItem: ProgramFragment.OnListFragmentInteractionListener_program?
) : RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val program = v.tag as Program
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListenerItem?.onListFragmentInteraction_program(program)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_program, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = mValues[position]
        holder.mIdView.text = program.id
        holder.mContentView.text = program.name

        with(holder.mView) {
            tag = program
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.program_id
        val mContentView: TextView = mView.program_name

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
