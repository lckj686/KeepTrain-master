package com.leon.train

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PickerAdapter(
    private val context: Context,
    private val dataList: List<ItemData>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<PickerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.tv.text = dataList[position].content
        holder.layoutItem.setOnClickListener {
            recyclerView.smoothScrollToPosition(position)
        }
        holder.layoutItem.isSelected = dataList[position].isSelect
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tv: TextView
        var targetView: View
        var layoutItem: View

        init {
            tv = itemView.findViewById<View>(R.id.picker_item) as TextView
            targetView = itemView.findViewById(R.id.viewTarget)
            layoutItem = itemView.findViewById(R.id.layoutItem)
        }
    }

}