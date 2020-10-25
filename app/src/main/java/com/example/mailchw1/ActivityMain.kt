package com.example.mailchw1

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class ActivityMain() : AppCompatActivity(), NumListFragment.IListener {
    companion object {

        const val TAG_DETAILS = "DETAILS"
        const val TAG_NUMBERS_LIST = "NUM_LIST"
        const val NUM_ARRAY = "ARRAY"
        const val MIN_ARR_VAL = 1
        const val MAX_ARR_VAL = 100
    }

    var numbers = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sth = savedInstanceState?.getIntegerArrayList(NUM_ARRAY)
        if (sth == null)
            numbers.addAll(MIN_ARR_VAL..MAX_ARR_VAL)
        else
            numbers = sth


        var bundle = Bundle().apply {
            putIntegerArrayList(NUM_ARRAY, numbers)
        }

        var fragment = NumListFragment().apply {
            arguments = bundle
        }

        supportFragmentManager.beginTransaction().add(R.id.main_ll, fragment, TAG_NUMBERS_LIST)
            .commitAllowingStateLoss()

        if (savedInstanceState != null) {
            checkDetails()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    protected fun showDetails(number: Int?) {
        if (number == null)
            return
        val detailsFragment = NumDetailsFragment.newInstance(number)
        supportFragmentManager.beginTransaction().replace(R.id.main_ll, detailsFragment)
            .addToBackStack(
                TAG_DETAILS
            ).commitAllowingStateLoss()
    }

    protected fun checkDetails() {
        val details = supportFragmentManager.findFragmentByTag(TAG_DETAILS) as? NumDetailsFragment
        if (details != null) {
            supportFragmentManager.beginTransaction().remove(details).commitAllowingStateLoss()
            val number = details.getNumber()
            showDetails(number)
        }
    }

    override fun onNumClicked(num: Int) {
        showDetails(num)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        numbers = savedInstanceState?.getIntegerArrayList(NUM_ARRAY)!!
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putIntegerArrayList(NUM_ARRAY, numbers)
    }
}