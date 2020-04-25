package com.example.floclone.Content_Fragments


import android.annotation.SuppressLint
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
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*


class TodayFragment : Fragment() {
    lateinit var scrolldatePicker: DayScrollDatePicker
    lateinit var textView: TextView
    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var textView3: TextView
    var Mwezi:String=""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View
        view= inflater.inflate(R.layout.fragment_today, container, false)

            scrolldatePicker=view.findViewById(R.id.scroll_date_picker)
            textView=view.findViewById(R.id.datedayInfo)
            textView1=view.findViewById(R.id.periodProgress)
            textView2=view.findViewById(R.id.periodProgressInfo)
            textView3=view.findViewById(R.id.predictionStatement)





        readData()
        saveStartUp()





        return view
    }

    @SuppressLint("NewApi")
    fun readData(){


        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences("start", MODE_PRIVATE)
        val day = sharedPreferences.getInt("day",0)
        val month = sharedPreferences.getInt("month",0)
        val year = sharedPreferences.getInt("year",0)


        scrolldatePicker.setStartDate(day, month, year)
        scrolldatePicker.setEndDate(20,8,2020)
        scrolldatePicker.isSelected=true


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



        val pref: SharedPreferences = context!!.getSharedPreferences("daysLog", MODE_PRIVATE)
        val days = pref.getInt("days",0)



        val cyclePref: SharedPreferences = context!!.getSharedPreferences("cycle", MODE_PRIVATE)
        val cycleDays = cyclePref.getInt("cycleDays",0)



        //Here I take the date when the cycle began and add it with the cycle days to know when the next will start
        val cal: Calendar = Calendar.getInstance()
        cal.set(year,month,day)
        cal.add(Calendar.DATE,cycleDays)

        val next: Date =cal.time
        
      //  Toast.makeText(context,"NEXT :\t"+next, Toast.LENGTH_LONG).show()


        val date = Date(cal.timeInMillis)
        val sdf = SimpleDateFormat("dd-MMM-yyyy")
        println(sdf.format(date))
        val tarehe = sdf.format(date.time)



        //Setting Current Day for Logs
        val stdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDateandTime = stdf.format(Date())
        textView.text = currentDateandTime


        //val difDays:Days = Days.daysBetween(tarehe, currentDateandTime).getDays()



       //Getting current date Today date
        val  c:Calendar = Calendar.getInstance()
        val siku:Int = c.get(Calendar.DAY_OF_MONTH)
        val mwezi:Int= c.get(Calendar.MONTH)
        val mwaka:Int= c.get(Calendar.YEAR)


        //getting the future cycle days after I added the cycle Days
        val sikuu:Int = cal.get(Calendar.DAY_OF_MONTH)
        val mwezii:Int= cal.get(Calendar.MONTH)
        val mwakaa:Int= cal.get(Calendar.YEAR)



        val end=LocalDate.of(mwakaa,mwezii,sikuu)
        val start=LocalDate.of(mwaka,mwezi,siku)
        val difference=ChronoUnit.DAYS.between(start,end)


        //Toast.makeText(context,"Remaining days "+difference, Toast.LENGTH_LONG).show()

        if (difference.toInt() <= 10){


            textView1.setText(difference.toString()+"\tDays")
            textView2.setText("Low chance of getting pregnant")
            textView3.setText("Period In :")

        }

        if (difference.toInt() == 0){



            textView1.setText("Day\t"+1)
            textView2.visibility=View.GONE
            textView3.setText("Knock Knock !\n Period Might start Today")

            //Toast.makeText(context,"Days Diff\t"+Daysdifference, Toast.LENGTH_LONG).show()


        }

        if (difference.toInt() == 14){


            textView1.setText("Ovulation Day")
            textView2.setText("High chance of getting pregnant")
            textView3.setText("Get Ready for Ovulation")



        }


        if (difference.toInt() < 0){



            Toast.makeText(context,"Below Zero ", Toast.LENGTH_LONG).show()


            //Getting current date Today date
            val  calee:Calendar = Calendar.getInstance()
            calee.add(Calendar.DATE,days)
            val ends=calee.time

            //Getting current date Today date
            val  cul:Calendar = Calendar.getInstance()
            val periodCurrentDay:Int = cul.get(Calendar.DAY_OF_MONTH)
            val periodCurrentMont:Int= cul.get(Calendar.MONTH)
            val periodCurrentYear:Int= cul.get(Calendar.YEAR)


            //getting the future cycle days after I added the period Days
            val endDay:Int = calee.get(Calendar.DAY_OF_MONTH)
            val endMonth:Int= calee.get(Calendar.MONTH)
            val endYear:Int= calee.get(Calendar.YEAR)



            val PeriodEnd=LocalDate.of(endYear,endMonth,endDay)
            val PeriodStart=LocalDate.of(periodCurrentYear,periodCurrentMont,periodCurrentDay)

            val Daysdifference=ChronoUnit.DAYS.between(PeriodStart,PeriodEnd)
            val loop:Int=Daysdifference.toInt()


               for (i in 1..loop) {


                     Toast.makeText(context,"\t"+i, Toast.LENGTH_LONG).show()


                       textView1.setText("Day\t"+i)
                       textView2.visibility=View.GONE
                       textView3.setText("Period Time ")





                 }



        }



    }



    fun saveStartUp(){

        val startUpPreferences:SharedPreferences=context!!.getSharedPreferences("Login",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=startUpPreferences.edit()
        editor.putBoolean("saved",true)

        editor.commit()


    }


}
