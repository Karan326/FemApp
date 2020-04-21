package com.example.floclone.Fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import com.example.floclone.PredictingCycleSplashScreen
import com.example.floclone.R
import com.kofigyan.stateprogressbar.StateProgressBar
import com.shawnlin.numberpicker.NumberPicker

class SelectYearOfBirth : Fragment() {

    lateinit var stateProgressBar: StateProgressBar
    lateinit var button: Button
    lateinit var imageView: ImageView
    lateinit var spinner: Spinner
    lateinit var  textView:TextView



     var miaka:Int=0
     var now:Int=2020
    lateinit var radioButton: RadioButton
    lateinit var numberPicker: NumberPicker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view:View

        view=inflater.inflate(R.layout.fragment_select_year_of_birth, container, false)

            button = view.findViewById(R.id.birtYearNextButton)
            imageView = view.findViewById(R.id.goToAverageCycle)
            stateProgressBar = view.findViewById(R.id.birtYearCycleSeekbar)

            val descriptionData = arrayOf("Date", "Days", "Cycle", "Birth Year")



            stateProgressBar.setStateDescriptionData(descriptionData)
            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR)
            stateProgressBar.enableAnimationToCurrentState(true)


        spinner = view.findViewById(R.id.spinner)
        textView = view.findViewById(R.id.agelimit)

        val adapter = ArrayAdapter.createFromResource(context!!, R.array.Year, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)



        spinner.setOnItemSelectedListener(object : OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {



                if (spinner.selectedItemId <=0 ){


                    Toast.makeText(context,"Please Make A valid Choice",Toast.LENGTH_LONG).show()
                    button.visibility=View.GONE


                    return
                }

                val year: Int = spinner.getSelectedItem().toString().toInt()
                miaka = now - year
                textView.setText(Integer.toString(miaka))

                button.visibility=View.VISIBLE


            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })






            imageView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    getActivity()!!.supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectCycleDays()).addToBackStack(null).commit()

                }


            })



            button.setOnClickListener(object : View.OnClickListener {

                override fun onClick(p0: View?) {
                    val inten= Intent(context,PredictingCycleSplashScreen::class.java)
                    startActivity(inten)

                }
            })







            return view
    }


}
