package com.bbbond.moduleCommon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bbbond.moduleCommon.R
import com.bbbond.moduleCommon.data.ActItem


class CommonActionAdapter(content: Context, data: List<ActItem>) : BaseAdapter() {
    private var mInflater: LayoutInflater = LayoutInflater.from(content)
    private var mData: List<ActItem> = data

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View
        if (convertView == null) {
            holder = ViewHolder()
            view = mInflater.inflate(R.layout.common_action_list_item, null)
            holder.tv = view.findViewById(R.id.item_tv)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        holder.tv?.text = mData[position].title
        holder.tv?.contentDescription = mData[position].title
        return view
    }

    override fun getItem(position: Int): Any = mData[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mData.size

    public final class ViewHolder {
        var tv: TextView? = null
    }
}
