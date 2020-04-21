package com.example.floclone.Content_Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrewjapar.rangedatepicker.CalendarPicker

import com.example.floclone.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CalenderFragment : Fragment() {

   lateinit var calenderCalendarPicker:CalendarPicker;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_calender, container, false)

        calenderCalendarPicker=view.findViewById(R.id.calenderPick)


        val startDate = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
        val endDate = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
        endDate.add(Calendar.MONTH, 6) // Add 6 months ahead from current date

        calenderCalendarPicker.apply {
            setMode(CalendarPicker.SelectionMode.RANGE) // You can set it via XML
            setRangeDate(startDate.time, endDate.time)
            setSelectionDate(startDate.time, endDate.time)
        }







        return view
    }


}
