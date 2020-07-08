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
    lateinit var textView4: TextView
    var Mwezi:String=""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View
        view= inflater.inflate(R.layout.fragment_today, container, false)

            scrolldatePicker=view.findViewById(R.id.scroll_date_picker)
            textView=view.findViewById(R.id.datedayInfo)
            textView1=view.findViewById(R.id.periodProgress)
            textView2=view.findViewById(R.id.periodProgressInfo)
            textView3=view.findViewById(R.id.predictionStatement)
            textView4=view.findViewById(R.id.logCalenderTextView)

        readData()
        saveStartUp()


        return view
    }

    @SuppressLint("NewApi", "SimpleDateFormat")
    fun readData(){

        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences("start", MODE_PRIVATE)
        val day = sharedPreferences.getInt("day",0)
        val month = sharedPreferences.getInt("month",0)
        val year = sharedPreferences.getInt("year",0)


        scrolldatePicker.setStartDate(day, month, year)
        scrolldatePicker.setEndDate(20,8,2020)
        scrolldatePicker.isSelected=true

        val pref: SharedPreferences = context!!.getSharedPreferences("daysLog", MODE_PRIVATE)
        val days = pref.getInt("days",0)

        val cyclePref: SharedPreferences = context!!.getSharedPreferences("cycle", MODE_PRIVATE)
        val cycleDays = cyclePref.getInt("cycleDays",0)

        
        checkPeriodDays(days,year,month,day,cycleDays)

        //Setting Current Day for Logs
        val myDateFormat = SimpleDateFormat("dd MMMM yyyy")
        val currentDateAndTime = myDateFormat.format(Date())
        textView.text = currentDateAndTime

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    fun predictFuture(cycleDays: Int, days: Int, year: Int, month: Int, day: Int) {

        //Getting current date Today date
        val  c:Calendar = Calendar.getInstance()
        var siku:Int = c.get(Calendar.DAY_OF_MONTH)
        val mwezi:Int= c.get(Calendar.MONTH)
        val mwaka:Int= c.get(Calendar.YEAR)

        //getting the future cycle days after I added the cycle Days
        //Here I take the date when the cycle began and add it with the cycle days to know when the next cycle will start
        val cal: Calendar = Calendar.getInstance()
        cal.set(year,month,day)
        cal.add(Calendar.DATE,cycleDays)
        val sikuu:Int = cal.get(Calendar.DAY_OF_MONTH)
        val mwezii:Int= cal.get(Calendar.MONTH)
        val mwakaa:Int= cal.get(Calendar.YEAR)


        val end=LocalDate.of(mwakaa,mwezii,sikuu)
        val start=LocalDate.of(mwaka,mwezi,siku)
        val difference=ChronoUnit.DAYS.between(start,end)

       // ToastMessage("Remaining days $difference")

      if (difference.toInt() == 14){

            textView1.text="Ovulation Day"
            textView2.text="High chance of getting pregnant"
            textView3.text="Get Ready for Ovulation"

        }else if (difference.toInt() <= 10){

            textView1.text="$difference\tDays"
            textView2.text="Low chance of getting pregnant"
            textView3.text="Period In :"

        }else if (difference.toInt() == 0){

            val predictNextMonth=month+1
            var whichYear=year
            if (month==12){

                whichYear=year+1
                return
            }

            checkPeriodDays(days,whichYear, predictNextMonth, day, cycleDays)

        }else if (difference.toInt() > 20){

            val test=siku+1
            textView1.text="Day $test"
            textView2.visibility=View.GONE
            textView3.text="Current Cycle :"


        }


    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    fun checkPeriodDays(days: Int, year: Int, month: Int, day: Int, cycleDays: Int) {

        val periodCalender:Calendar= Calendar.getInstance()
        periodCalender.set(year,month,day)
        periodCalender.add(Calendar.DATE,days)
        val periodEndMonth=periodCalender.get(Calendar.MONTH)
        val periodEndDay=periodCalender.get(Calendar.DAY_OF_MONTH)
        val periodEndYear=periodCalender.get(Calendar.YEAR)


        val todayCalender:Calendar= Calendar.getInstance()
        val today:Int = todayCalender.get(Calendar.DAY_OF_MONTH)
        val thisMonth:Int= todayCalender.get(Calendar.MONTH)
        val thisYear:Int= todayCalender.get(Calendar.YEAR)


        val endDate=LocalDate.of(periodEndYear,periodEndMonth,periodEndDay)
        val todaysDate=LocalDate.of(thisYear,thisMonth,today)
        val differenceBtwnToday=ChronoUnit.DAYS.between(todaysDate,endDate)

        /*ToastMessage("$periodEndDay/$periodEndMonth/$periodEndYear")
       ToastMessage("$today/$thisMonth/$thisYear")*/

        var dayOrDays=""

        if (differenceBtwnToday.toInt()>2){
            dayOrDays="Days"

            textView1.text="$differenceBtwnToday $dayOrDays"
            textView2.visibility=View.GONE
            textView3.text="Period Ends In :"
            textView4.text="Log Days"

        }else if(differenceBtwnToday.toInt() == 0){

            dayOrDays="Today"

            textView1.text= dayOrDays
            textView2.visibility=View.GONE
            textView3.text="Period Ends:"
            textView4.text="Log Days"

        }else if(differenceBtwnToday.toInt() < 0){

           predictFuture(cycleDays, days,year, month,day)



        }

        else{

            dayOrDays="Day"

            textView1.text="$differenceBtwnToday $dayOrDays"
            textView2.visibility=View.GONE
            textView3.text="Period Ends In :"
        }




    }


    private fun saveStartUp(){

        val startUpPreferences:SharedPreferences=context!!.getSharedPreferences("Login",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=startUpPreferences.edit()
        editor.putBoolean("saved",true)

        editor.commit()


    }

    fun ToastMessage (message:String){

        Toast.makeText(context,message, Toast.LENGTH_LONG).show()

    }


}


