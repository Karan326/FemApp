package com.example.floclone.Fragment


import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.example.floclone.R
import com.kofigyan.stateprogressbar.StateProgressBar
import com.shawnlin.numberpicker.NumberPicker
import java.lang.String
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SelectCycleDays : Fragment() {

    lateinit var stateProgressBar: StateProgressBar
    lateinit var button: Button
    lateinit var imageView: ImageView
    lateinit var radioButton: RadioButton
    lateinit var numberPicker: NumberPicker
    var CycleDays:Int=0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View

        view = inflater.inflate(R.layout.fragment_select_cycle_days, container, false)

        button = view.findViewById(R.id.cycleNextButton)
        radioButton = view.findViewById(R.id.cycleRadioButton)
        imageView = view.findViewById(R.id.goToCycle)
        numberPicker = view.findViewById(R.id.cyclenumber_picker)


        val descriptionData = arrayOf("Date", "Days", "Cycle", "Birth Year")


        stateProgressBar = view.findViewById(R.id.cycleSeekbar)
        stateProgressBar.setStateDescriptionData(descriptionData)
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
        stateProgressBar.enableAnimationToCurrentState(true)


        // Using string values
// IMPORTANT! setMinValue to 1 and call setDisplayedValues after setMinValue and setMaxValue
        val data =
            arrayOf(
                "21 Days","22 Days","23 Days","24 Days","25 Days","26 Days","27 Days","28 Days","29 Days","30 Days",
            "31 Days","32 Days","33 Days","34 Days","35 Days","36 Days","37 Days","38 Days","39 Days","40 Days",
            "41 Days","42 Days","43 Days","44 Days","45 Days","46 Days","47 Days","48 Days","49 Days","50 Days",
            "51 Days","52 Days","53 Days","54 Days","55 Days","56 Days","57 Days","58 Days","59 Days","60 Days",
            "61 Days","62 Days","63 Days","64 Days","65 Days","66 Days","67 Days","68 Days","69 Days","70 Days",
            "71 Days","72 Days","73 Days","74 Days","75 Days","76 Days","77 Days","78 Days","79 Days","80 Days",
            "81 Days","82 Days","83 Days","84 Days","85 Days","86 Days","87 Days","88 Days","89 Days","90 Days",
            "91 Days","92 Days","93 Days","94 Days","95 Days","96 Days","97 Days","98 Days","99 Days","100 Days")

        val input = arrayOf(R.array.period_days)

        numberPicker.minValue = 1
        numberPicker.maxValue = data.size
        numberPicker.displayedValues = data
        numberPicker.value = 1



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
                getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectDaysFragment()).addToBackStack(null).commit()

            }


        })



        radioButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                Toast.makeText(context, "You can choose Later", Toast.LENGTH_LONG).show()
                button.visibility = View.VISIBLE

            }

        })


        // OnClickListener
        numberPicker.setOnClickListener { Log.d(ContentValues.TAG, "Click on current value")



        }


        // OnValueChangeListener
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->


            if(newVal<=10){

                var value:Int=newVal+20
                CycleDays=value

            }

            if(newVal<=80){

                var value:Int=newVal+20
                CycleDays=value


            }


            button.visibility = View.VISIBLE

            Toast.makeText(container!!.context, ""+CycleDays.toString()+"\t Days", Toast.LENGTH_SHORT).show()




        }

        button.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                //   getSupportFragmentManager().beginTransaction().remove().commit();
             getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectYearOfBirth()).addToBackStack(null).commit()

                radioButton.visibility = View.GONE
                button.visibility = View.GONE


                val prefs: SharedPreferences = context!!.getSharedPreferences("cycle", Context.MODE_PRIVATE)
                val editor : SharedPreferences.Editor =prefs.edit()


                editor.putInt("cycleDays",CycleDays)

                editor.commit()



            }
        })




        return view


    }

}
