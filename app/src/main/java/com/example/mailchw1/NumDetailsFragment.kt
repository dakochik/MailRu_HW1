package com.example.mailchw1

import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer

class NumDetailsFragment : Fragment() {
    companion object{
        const val EXTRAS_NUMBER = "NUMBER"

        fun newInstance(number : Int) : NumDetailsFragment{
            val extras = Bundle().apply {
                putInt(EXTRAS_NUMBER, number)
            }

            val fragment = NumDetailsFragment().apply{
                arguments = extras
            }
            return  fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.num_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val number = getNumber()
        if(number != null){
            val tView = view.findViewById<TextView>(R.id.num_inf_tv)
            tView.text = number.toString()
            if(number % 2 ==0)
                tView.setTextColor(RED)
            else
                tView.setTextColor(BLUE)
        }

        view.findViewById<Button>(R.id.num_inf_btn).setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    fun getNumber() : Int?{
        return arguments?.getInt(EXTRAS_NUMBER)
    }
}