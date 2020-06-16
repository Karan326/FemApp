package com.example.floclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class ReportsActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var pieChart:PieChart
    lateinit var list: MutableList<PieEntry>
    lateinit var month:List<String>
    lateinit var cycleCount:List<Float>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        toolbar = findViewById(R.id.reportsToolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        pieChart = findViewById(R.id.cycleChart)

        list=ArrayList<PieEntry>()
        month=ArrayList<String>()
        cycleCount=ArrayList<Float>()


        month= listOf("Jan","Feb","March","April","May")
        cycleCount= listOf(10.6f,11.2f,13.5f,10.2f,10.8f)



        for (i in 0 until cycleCount.size) {


            list.add(PieEntry(cycleCount[i],month[i]))




        }

        val dataSet = PieDataSet(list, "Period Log")
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = PieData(dataSet)
        pieChart.data=data
        pieChart.animateX(3000)
        pieChart.invalidate();


    }
}
