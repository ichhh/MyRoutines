package com.chernov.ivan.myroutines.util

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.chernov.ivan.myroutines.ItemFragment.OnListFragmentInteractionListener
import com.chernov.ivan.myroutines.R
import com.chernov.ivan.myroutines.model.ItemEntity

import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [ItemEntity] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ItemRecyclerViewAdapter(
    private val mValues: List<ItemEntity>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as ItemEntity
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction_item(item,item.programId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = item.name

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_id
        val mContentView: TextView = mView.item_name

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    interface ListItemListener {
        fun editNote(noteId: Int)
        fun onItemSelectionChanged()
    }
}
