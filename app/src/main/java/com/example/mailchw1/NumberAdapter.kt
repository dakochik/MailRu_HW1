package com.example.mailchw1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(val data : List<Int>, val listener : NumberViewHolder.IListener) : RecyclerView.Adapter<NumberViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.item_number, parent, false)

        return NumberViewHolder(layout, listener)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}