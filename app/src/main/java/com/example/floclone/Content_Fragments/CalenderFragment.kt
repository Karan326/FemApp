package com.example.floclone.Content_Fragments


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrewjapar.rangedatepicker.CalendarPicker

import com.example.floclone.R
import java.text.SimpleDateFormat
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




        //readData()



        return view
    }

    @SuppressLint("NewApi", "SimpleDateFormat")
    fun readData(){

        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences("start", Context.MODE_PRIVATE)
        val day = sharedPreferences.getInt("day",0)
        val month = sharedPreferences.getInt("month",0)
        val year = sharedPreferences.getInt("year",0)



        val pref: SharedPreferences = context!!.getSharedPreferences("daysLog", Context.MODE_PRIVATE)
        val days = pref.getInt("days",0)

        val cyclePref: SharedPreferences = context!!.getSharedPreferences("cycle", Context.MODE_PRIVATE)
        val cycleDays = cyclePref.getInt("cycleDays",0)


        //Setting Current Day for Logs
        val myDateFormat = SimpleDateFormat("dd MMMM yyyy")
        val currentDateAndTime = myDateFormat.format(Date())
       // textView.text = currentDateAndTime

        val endCalender=Calendar.getInstance()
        endCalender.set(year,month,day)
        endCalender.add(Calendar.DAY_OF_MONTH,3)

        val  endDate=endCalender.time

        calenderCalendarPicker.setRangeDate(endDate,Date())


    }


}
