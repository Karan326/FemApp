package com.example.floclone.Fragment


import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.floclone.MainActivity

import com.example.floclone.R
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_track_my_cycle.*
import kotlinx.android.synthetic.main.fragment_select_date.*
import java.util.*

class SelectDateFragment : Fragment() {

    // lateinit var chartProgressBar: ChartProgressBar
    lateinit var stateProgressBar: StateProgressBar
    lateinit var datePicker: DatePicker
    lateinit var button: Button
    lateinit var  imageView: ImageView
    lateinit var radioButton: RadioButton

     var  month: String=""
    var yearr: String=""
    var  day: String=""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view:View

        view=inflater.inflate(R.layout.fragment_select_date, container, false)

        val descriptionData = arrayOf("Date", "Days", "Cycle", "Birth Year")
        datePicker = view.findViewById(R.id.periodDatePicker)
        button=view.findViewById(R.id.nextButton)
        radioButton=view.findViewById(R.id.dontRememeberRadioButton)


        stateProgressBar = view.findViewById(R.id.trackCycleSeekbar)
        stateProgressBar.setStateDescriptionData(descriptionData)
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
        stateProgressBar.enableAnimationToCurrentState(true)




        datePicker.maxDate = Date().time
        val today = Calendar.getInstance()

       datePicker.init(today[Calendar.YEAR], today[Calendar.MONTH], today[Calendar.DAY_OF_MONTH], DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->

                // Toast.makeText(applicationContext, "onDateChanged", Toast.LENGTH_SHORT).show()


                month= monthOfYear.toString()
                 day= dayOfMonth.toString()
                yearr= year.toString()

          // Toast.makeText(context, "DAY IS :\t "+day+"\n"+"MONTH IS :\t"+month+"\n"+"YEAR IS :\t"+yearr, Toast.LENGTH_SHORT).show()

                button.visibility = View.VISIBLE

                // Toast.makeText(applicationContext,"Date\t"+month+"-\t"+day+"-\t"+year,Toast.LENGTH_LONG).show()
            })

        radioButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                Toast.makeText(context,"You can choose Later", Toast.LENGTH_LONG).show()
                button.visibility = View.VISIBLE

            }

        })




        button.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectDaysFragment()).addToBackStack(null).commit()
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
                radioButton.visibility=View.GONE
                button.visibility=View.GONE




                val prefs:SharedPreferences= context!!.getSharedPreferences("start",MODE_PRIVATE)
                val editor : SharedPreferences.Editor =prefs.edit()

                editor.putInt("year",yearr.toInt())
                editor.putInt("month",month.toInt())
                editor.putInt("day",day.toInt())

                editor.commit()



            }
        })

        return view
    }




}
