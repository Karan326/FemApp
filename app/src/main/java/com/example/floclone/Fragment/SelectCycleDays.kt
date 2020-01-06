package com.example.floclone.Fragment


import android.content.ContentValues
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
            arrayOf("1", "2", "3", "4", "5", "Select Days", "6", "7", "8", "9", "10", "11", "12")
        val input = arrayOf(R.array.period_days)

        numberPicker.minValue = 1
        numberPicker.maxValue = data.size
        numberPicker.displayedValues = data
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
                getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectDaysFragment()).addToBackStack(null).commit()

            }


        })



        radioButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                Toast.makeText(context, "You can choose Later", Toast.LENGTH_LONG).show()

            }

        })


        // OnClickListener
        numberPicker.setOnClickListener { Log.d(ContentValues.TAG, "Click on current value") }


        // OnValueChangeListener
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            Log.d(
                ContentValues.TAG,
                String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal)
            )

            // numberPicker.removeViewAt(6)

            val value: Int

            if (newVal == 6) {

                Toast.makeText(
                    container!!.context,
                    "Select a valid number of days",
                    Toast.LENGTH_LONG
                ).show()
                button.visibility = View.GONE


                return@setOnValueChangedListener

            }

            if (newVal < 6) {

                value = newVal
                //  exactValue+1

                Toast.makeText(container!!.context, "less:\t" + value + "\tdays", Toast.LENGTH_SHORT).show()


            } else if (newVal > 6) {

                value = newVal - 1

                Toast.makeText(container!!.context, "" + value + "\tdays", Toast.LENGTH_SHORT).show()
                button.visibility = View.VISIBLE

            }


        }

        button.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                //   getSupportFragmentManager().beginTransaction().remove().commit();
             getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectYearOfBirth()).addToBackStack(null).commit()

                radioButton.visibility = View.GONE
                button.visibility = View.GONE

            }
        })




        return view


    }

}
