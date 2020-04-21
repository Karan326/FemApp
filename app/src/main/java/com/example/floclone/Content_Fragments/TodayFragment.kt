package com.example.floclone.Content_Fragments


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.floclone.R
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker
import java.text.SimpleDateFormat
import java.util.*

class TodayFragment : Fragment() {
    lateinit var scrolldatePicker: DayScrollDatePicker
    lateinit var textView: TextView
    var Mwezi:String=""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View
        view= inflater.inflate(R.layout.fragment_today, container, false)

            scrolldatePicker=view.findViewById(R.id.scroll_date_picker)
            textView=view.findViewById(R.id.datedayInfo)





        readData()
        saveStartUp()





        return view
    }

    fun readData(){


        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences("start", MODE_PRIVATE)
        val day = sharedPreferences.getInt("day",0)
        val month = sharedPreferences.getInt("month",0)
        val year = sharedPreferences.getInt("year",0)


        scrolldatePicker.setStartDate(day, month, year)
        scrolldatePicker.setEndDate(20,8,2020)


        when(month){

           1 ->{
               Mwezi="January"
           }

            2 ->{
                Mwezi="February"
            }

            3 ->{
                Mwezi="March"
            }

            4 ->{

                Mwezi="April"

            }

            5 ->{

                Mwezi="May"

            }

            6 ->{

                Mwezi="June"
            }

            7 ->{

                Mwezi="July"

            }

            8 ->{

                Mwezi="August"

            }

            9 ->{

                Mwezi="September"


            }

            10 ->{

                Mwezi="October"

            }

            11 ->{

                Mwezi="November"

            }

            12 ->{
                Mwezi="December"


            }

        }


        //textView.setText(""+day+",\t"+Mwezi+"\t\t"+year)


        val pref: SharedPreferences = context!!.getSharedPreferences("daysLog", MODE_PRIVATE)
        val days = pref.getInt("days",0)

        Toast.makeText(context!!,"Ulinyesha siku :\t"+days,Toast.LENGTH_LONG).show()


        val cyclePref: SharedPreferences = context!!.getSharedPreferences("cycle", MODE_PRIVATE)
        val cycleDays = cyclePref.getInt("cycleDays",0)

        Toast.makeText(context!!,"Cycle Days:\t"+cycleDays,Toast.LENGTH_LONG).show()



        val cal: Calendar = Calendar.getInstance()

        cal.set(year,month,day)
        cal.add(Calendar.DATE,cycleDays)

        val next: Date =cal.time
        
        Toast.makeText(context,"NEXT :\t"+next, Toast.LENGTH_LONG).show()


        val date = Date(cal.timeInMillis)
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        println(sdf.format(date))
        val tarehe = sdf.format(date.time)

        Toast.makeText(context,"TIME :\t"+tarehe, Toast.LENGTH_LONG).show()


        //Setting Current Day for Logs
        val stdf = SimpleDateFormat("dd-MM-yyyy")
        val currentDateandTime = stdf.format(Date())
        textView.text = currentDateandTime

    }



    fun saveStartUp(){

        val startUpPreferences:SharedPreferences=context!!.getSharedPreferences("Login",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=startUpPreferences.edit()
        editor.putBoolean("saved",true)

        editor.commit()


    }


}
