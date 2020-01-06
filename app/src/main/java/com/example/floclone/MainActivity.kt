package com.example.floclone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import devs.mulham.horizontalcalendar.HorizontalCalendar
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    fun navigateToTrackMyCycle(view: View) {

        intent= Intent(applicationContext,TrackMyCycleActivity::class.java)
        startActivity(intent)
        finish()



    }
}
