package com.example.floclone

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class SettingsActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    lateinit var periodSpinner:Spinner
    lateinit var periodtextView: TextView
    lateinit var toolbar: Toolbar

    lateinit var cycleSpinner:Spinner
    lateinit var cycletextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        toolbar=findViewById(R.id.settingToolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        periodtextView=findViewById(R.id.current_period_display)
        cycletextView=findViewById(R.id.current_cycle_display)

        periodSpinner=findViewById(R.id.periodSpinn)
        cycleSpinner=findViewById(R.id.cycleSpinn)

        val adapter :ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(applicationContext,R.array.period_days,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //For Cycle Spinner
        val adapter1 :ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(applicationContext,R.array.cycle_days,android.R.layout.simple_spinner_item)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        periodSpinner.adapter=adapter
        periodSpinner.onItemSelectedListener=this

        //For Cycle Spinner
        cycleSpinner.adapter=adapter1
        cycleSpinner.onItemSelectedListener=this


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

        readData()

    }

    @SuppressLint("SetTextI18n")
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


        val positionSelected:Int=periodSpinner.selectedItemPosition
        (view as TextView).text =null

        if (positionSelected == 0){

            Toast.makeText(applicationContext,"No changes",Toast.LENGTH_SHORT).show()
            readData()

        }else{

            val value=parent!!.getItemAtPosition(position).toString()
            removePeriodValues()
            updatePeriodDays(value)
            readData()



        }


    }

    fun readData(){

        val pref: SharedPreferences = application.getSharedPreferences("daysLog", Context.MODE_PRIVATE)
        val days = pref.getInt("days",0)

        periodtextView.text="$days Days"

        val cyclePref: SharedPreferences = applicationContext.getSharedPreferences("cycle", Context.MODE_PRIVATE)
        val cycleDays = cyclePref.getInt("cycleDays",0)

        cycletextView.text="$cycleDays Days"


    }

    fun updatePeriodDays(value: String) {

        val toInt=Integer.parseInt(value)

        val pref: SharedPreferences = application.getSharedPreferences("daysLog", Context.MODE_PRIVATE)
       val editor:SharedPreferences.Editor=pref.edit()
        editor.putInt("days",toInt)
        editor.apply()


        Toast.makeText(applicationContext,"Updated in Method ${toInt}",Toast.LENGTH_SHORT).show()


    }

    fun removePeriodValues(){

        val sharedPreferences:SharedPreferences=applicationContext.getSharedPreferences("daysLog", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.remove("days")
        editor.apply()


    }


}
