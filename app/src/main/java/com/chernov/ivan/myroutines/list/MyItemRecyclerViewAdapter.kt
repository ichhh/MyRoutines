package com.chernov.ivan.myroutines.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.chernov.ivan.myroutines.list.ItemFragment.OnListFragmentInteractionListener_item
import com.chernov.ivan.myroutines.R
import com.chernov.ivan.myroutines.model.ProgramItem

import kotlinx.android.synthetic.main.fragment_item.view.*



class MyItemRecyclerViewAdapter(
    private val mValues: List<ProgramItem>,
    private val mListenerItem: OnListFragmentInteractionListener_item?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as ProgramItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListenerItem?.onListFragmentInteraction_item(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
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
}
