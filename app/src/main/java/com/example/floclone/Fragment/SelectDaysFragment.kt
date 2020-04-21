package com.example.floclone.Fragment


import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.floclone.R
import com.kofigyan.stateprogressbar.StateProgressBar
import com.shawnlin.numberpicker.NumberPicker
import java.lang.String
import java.util.*


class SelectDaysFragment : Fragment() {

    lateinit var stateProgressBar: StateProgressBar
    lateinit var button: Button
    lateinit var  imageView: ImageView
    lateinit var radioButton: RadioButton
    lateinit var numberPicker: NumberPicker

    var themDays:Int=0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val view:View
        view= inflater.inflate(R.layout.fragment_select_days, container, false)

        button=view.findViewById(R.id.nextButtonSelectedDays)
        radioButton=view.findViewById(R.id.dontRememeberRadioButtonSelectedDays)
        imageView=view.findViewById(R.id.goToDate)
        numberPicker=view.findViewById(R.id.number_picker)


        val descriptionData = arrayOf("Date", "Days", "Cycle", "Birth Year")


        stateProgressBar = view.findViewById(R.id.trackDaysCycleSeekbar)
        stateProgressBar.setStateDescriptionData(descriptionData)
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
        stateProgressBar.enableAnimationToCurrentState(true)


        // Using string values
       // IMPORTANT! setMinValue to 1 and call setDisplayedValues after setMinValue and setMaxValue
        val data = arrayOf("1 Day", "2 Days", "3 Days", "4 Days", "5 Days",
            "Select Days", "6 Days", "7 Days", "8 Days", "9 Days","10 Days",
            "11 Days","12 Days ","13 Days","14 Days")
        val input= arrayOf(R.array.period_days)

        numberPicker.minValue = 1
        numberPicker.maxValue = data.size
        numberPicker.displayedValues=data
        numberPicker.value = 6

       /* // Set value
        numberPicker.maxValue=59
        numberPicker.minValue=0
        numberPicker.value=3
*/
        // Set fading edge enabled
        numberPicker.isFadingEdgeEnabled = true
        // Set scroller enabled
        numberPicker.isScrollerEnabled = true
        // Set wrap selector wheel
        numberPicker.wrapSelectorWheel = true

        // Set text color
        numberPicker.setTextColor(ContextCompat.getColor(container!!.context, R.color.colorPrimary))
        numberPicker.setTextColorResource(R.color.colorPrimary)

        // Set selected text color
        numberPicker.setSelectedTextColor(ContextCompat.getColor(container!!.context, R.color.colorPrimaryDark))
        numberPicker.setSelectedTextColorResource(R.color.colorPrimaryDark)

        // Set divider color
        numberPicker.setDividerColor(ContextCompat.getColor(container!!.context, R.color.colorRed))
        numberPicker.setDividerColorResource(R.color.colorRed);

        imageView.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectDateFragment()).addToBackStack(null).commit()

                val prefs: SharedPreferences = context!!.getSharedPreferences("start", Context.MODE_PRIVATE)
                val editor : SharedPreferences.Editor =prefs.edit()

                editor.clear()
                editor.commit()
            }


        })

        radioButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                Toast.makeText(context,"You can choose Later", Toast.LENGTH_LONG).show()
                button.visibility=View.VISIBLE

            }

        })


        // OnClickListener
        numberPicker.setOnClickListener { Log.d(TAG, "Click on current value") }


        // OnValueChangeListener
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            Log.d(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal))

            //numberPicker.removeViewAt(6)

            val value:Int

            if (newVal == 6){

                Toast.makeText(container!!.context,"Select a valid number of days",Toast.LENGTH_LONG).show()
                button.visibility=View.GONE


                return@setOnValueChangedListener

            }

            if (newVal < 6){

                value= newVal

                button.visibility=View.VISIBLE

                themDays=value

            }else if (newVal>6){

                value=newVal-1

                button.visibility=View.VISIBLE

                themDays=value

            }

            Toast.makeText(container!!.context,""+themDays+"\tdays",Toast.LENGTH_SHORT).show()



        }

        button.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

             getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectCycleDays()).addToBackStack(null).commit()

                radioButton.visibility=View.GONE
                button.visibility=View.GONE

                saveDays()

            }
        })

        return view
    }

    fun saveDays(){


        val prefs:SharedPreferences= context!!.getSharedPreferences("daysLog", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor =prefs.edit()

        editor.putInt("days",themDays)

        editor.commit()


        Toast.makeText(context!!,""+themDays,Toast.LENGTH_LONG).show()



    }


}
