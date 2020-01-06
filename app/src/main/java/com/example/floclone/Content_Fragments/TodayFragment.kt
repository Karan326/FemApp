package com.example.floclone.Content_Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.floclone.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker

class TodayFragment : Fragment() {
    lateinit var scrolldatePicker: DayScrollDatePicker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View
        view= inflater.inflate(R.layout.fragment_today, container, false)

            scrolldatePicker=view.findViewById(R.id.scroll_date_picker)
            scrolldatePicker.setStartDate(10, 12, 2019)
            scrolldatePicker.setEndDate(5, 2, 2020)






        return view
    }


}
