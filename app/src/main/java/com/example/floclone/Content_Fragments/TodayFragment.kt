package com.example.floclone.Content_Fragments


import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
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


        val date = Date(cal.timeInMillis)
        val sdf = SimpleDateFormat("dd-MMM-yyyy")
        println(sdf.format(date))

        //Setting Current Day for Logs
        val stdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDateandTime = stdf.format(Date())
        textView.text = currentDateandTime


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

        Toast.makeText(context,"Remaining days "+difference, Toast.LENGTH_LONG).show()


        //Ovulation usually occurs on the Mid days between the cycle days
        //That is cycle days divided by 2
        if (difference.toInt() == 14){

            textView1.setText("Ovulation Day")
            textView2.setText("High chance of getting pregnant")
            textView3.setText("Get Ready for Ovulation")
        }

        //Getting pregnant before ovulation is almost impossible
        //The ovum has't been Released yet so fertilization can't happen
        if (difference.toInt() <= 10){

            textView1.setText(difference.toString()+"\tDays")
            textView2.setText("Low chance of getting pregnant")
            textView3.setText("Period In :")

        }
        //This is when the period starts on day 0 which is day one for the periods
        if (difference.toInt() == 0){


            textView2.visibility=View.GONE
            textView1.visibility=View.GONE
            textView3.setText("Period Might start Today")

        }

        //So basically this is the Day the period begins and we have to calculate for the next too
        if (difference.toInt() <= 0){

            predictFuture(cycleDays,days)

        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun predictFuture(cycleDays: Int, days: Int) {

        //Showing the user which day of their period they are in
        val calculate:Calendar= Calendar.getInstance()
        calculate.add(Calendar.DATE,days)

        val todayCalender:Calendar= Calendar.getInstance()
        val todayDate=todayCalender.get(Calendar.DAY_OF_MONTH)
        val todayMonth=todayCalender.get(Calendar.MONTH)
        val todayYear=todayCalender.get(Calendar.YEAR)

        val endPeriodDay=calculate.get(Calendar.DAY_OF_MONTH)
        val endPeriodMonth=calculate.get(Calendar.MONTH)
        val endPeriodYear=calculate.get(Calendar.YEAR)


        val PeriodStarts=LocalDate.of(todayYear,todayMonth,todayDate)
        val PeriodEnds=LocalDate.of(endPeriodYear,endPeriodMonth,endPeriodDay)


        val calculatedDays=ChronoUnit.DAYS.between(PeriodStarts,PeriodEnds)


        val number_of_days:Int=calculatedDays.toInt()
        Toast.makeText(context,"Period will End in"+number_of_days.toInt()+"\t Days",Toast.LENGTH_LONG).show()



        //So basically here is day 1 of the period
        //I want to calculate the next period when it'll begin
        //I will still use the data achieved which was the cycle days

        //Getting the Calender date for that day so that I can add the next Month Period
        val  c:Calendar = Calendar.getInstance()
        val siku:Int = c.get(Calendar.DAY_OF_MONTH)
        val mwezi:Int= c.get(Calendar.MONTH)
        val mwaka:Int= c.get(Calendar.YEAR)

        //Here I take the date when the cycle began and add it with the cycle days to know when the next will start
        val cal: Calendar = Calendar.getInstance()
        cal.set(mwaka,mwezi,siku)
        cal.add(Calendar.DATE,cycleDays)


        //getting the future cycle days after I added the period Days
        val endDay:Int = cal.get(Calendar.DAY_OF_MONTH)
        val endMonth:Int= cal.get(Calendar.MONTH)
        val endYear:Int= cal.get(Calendar.YEAR)

        //The day the first period to the next period
        val PeriodStart=LocalDate.of(mwaka,mwezi,siku)
        val PeriodEnd=LocalDate.of(endYear,endMonth,endDay)

        val Daysdifference=ChronoUnit.DAYS.between(PeriodStart,PeriodEnd)

      /*  Toast.makeText(context,"Next period starts in:\t"+Daysdifference.toInt()+"\tDays",Toast.LENGTH_LONG).show()
        Toast.makeText(context,"Starts on:\t"+endDay.toInt()+"\tof\t"+endMonth.toInt(),Toast.LENGTH_LONG).show()*/




    }



    fun saveStartUp(){

        val startUpPreferences:SharedPreferences=context!!.getSharedPreferences("Login",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=startUpPreferences.edit()
        editor.putBoolean("saved",true)

        editor.commit()


    }


}
