package com.example.mailchw1

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class NumListFragment : Fragment() {
    companion object{
        const val NUM_ARRAY = "ARRAY"
        const val MIN_ARR_VAL = 1
        const val MAX_ARR_VAL = 100
    }

    interface IListener{
        fun onNumClicked(num : Int)
    }

    protected var listener : IListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = requireActivity() as? IListener
    }

    var numbers = arrayListOf<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var sth = arguments?.getIntegerArrayList(NUM_ARRAY)
        var savedNums = savedInstanceState?.getIntegerArrayList(NUM_ARRAY)
        if(savedNums != null){
            numbers = savedNums
        }
        else {
            if (sth != null)
                numbers  = sth
            else
                numbers.addAll(MIN_ARR_VAL..MAX_ARR_VAL)
        }

        return inflater.inflate(R.layout.activity_with_fragment, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numAdapter = NumberAdapter(numbers, NumberClickHandler())

        view.findViewById<Button>(R.id.btn_add_num).setOnClickListener {
            numbers.add(if (numbers.isEmpty()) 1 else (numbers.last() + 1))
            numAdapter.notifyItemRangeChanged(0,numbers.size)
        }


        view.findViewById<RecyclerView>(R.id.first_fr_recycler).apply {
            layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.columns_num))
            adapter = numAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putIntegerArrayList(ActivityMain.NUM_ARRAY, numbers)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        var list = savedInstanceState?.getIntegerArrayList(NUM_ARRAY)
        if(list != null)
            numbers = list
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    inner class NumberClickHandler : NumberViewHolder.IListener{
        override fun onNumClicked(pos: Int) {
            val num  = numbers[pos]

            listener?.onNumClicked(num)
        }
    }
}