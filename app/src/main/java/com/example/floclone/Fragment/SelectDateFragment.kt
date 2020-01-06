package com.example.floclone.Fragment


import android.content.Intent
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view:View

        view=inflater.inflate(R.layout.fragment_select_date, container, false)

        val descriptionData = arrayOf("Date", "Days", "Cycle", "Birth Year")
        datePicker = view.findViewById(R.id.periodDatePicker)
        button=view.findViewById(R.id.nextButton)
        radioButton=view.findViewById(R.id.dontRememeberRadioButton)
        imageView=view.findViewById(R.id.backToOptions)

        stateProgressBar = view.findViewById(R.id.trackCycleSeekbar)
        stateProgressBar.setStateDescriptionData(descriptionData)
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
        stateProgressBar.enableAnimationToCurrentState(true)


        /* chartProgressBar=findViewById(R.id.trackCycleSeekbar)

         val dataList: ArrayList<BarData> = ArrayList()
         val data = BarData("Step 1", 9.4f, "")
         dataList.add(data)


         chartProgressBar.setDataList(dataList)
         chartProgressBar.build()*/


        datePicker.maxDate = Date().time
        val today = Calendar.getInstance()
       datePicker.init(today[Calendar.YEAR], today[Calendar.MONTH], today[Calendar.DAY_OF_MONTH], DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->

                // Toast.makeText(applicationContext, "onDateChanged", Toast.LENGTH_SHORT).show()

                val month: String = monthOfYear.toString()
                val day: String = dayOfMonth.toString()
                val year: String = year.toString()

                button.visibility = View.VISIBLE

                // Toast.makeText(applicationContext,"Date\t"+month+"-\t"+day+"-\t"+year,Toast.LENGTH_LONG).show()
            })

        radioButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                Toast.makeText(context,"You can choose Later", Toast.LENGTH_LONG).show()

            }

        })


        imageView.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                val intent=Intent(context,MainActivity::class.java)
                startActivity(intent)

            }


        })

        button.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                //   getSupportFragmentManager().beginTransaction().remove().commit();
                getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectDaysFragment()).addToBackStack(null).commit()
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
                radioButton.visibility=View.GONE
                button.visibility=View.GONE

            }
        })

        return view
    }



}
