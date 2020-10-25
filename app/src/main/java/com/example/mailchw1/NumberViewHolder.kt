package com.example.mailchw1

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberViewHolder(itemView : View, val listener : IListener) : RecyclerView.ViewHolder(itemView){
    protected val name: TextView

    interface IListener{
        fun onNumClicked(pos: Int)
    }

    init {
        name = itemView.findViewById(R.id.item_tv_name)

        itemView.setOnClickListener {
            listener.onNumClicked(adapterPosition)
        }
    }

    fun bind(item: Int) {
        name.text = item.toString()
        if(item % 2 == 0)
            name.setTextColor(Color.RED)
        else
            name.setTextColor(Color.BLUE)
    }
}