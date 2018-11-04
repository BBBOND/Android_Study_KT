package com.bbbond.moduleCommon.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bbbond.moduleCommon.R
import com.bbbond.moduleCommon.data.ActItem

class CommonActionRecyclerAdapter(private var data: List<ActItem>, private var listener: OnItemClickListener) : RecyclerView.Adapter<CommonActionRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_action_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = data[position].title
        holder.view.setOnClickListener {
            listener.onClick(it, position, data[position])
        }
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int, actItem: ActItem)
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.item_tv)
    }
}
