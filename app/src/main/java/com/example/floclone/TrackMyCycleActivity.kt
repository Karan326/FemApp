package com.example.floclone

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.DatePicker.OnDateChangedListener
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.floclone.Fragment.SelectDateFragment
import com.example.floclone.Fragment.SelectDaysFragment
import com.hadiidbouk.charts.ChartProgressBar
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_track_my_cycle.*
import java.util.*



class TrackMyCycleActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_my_cycle)




        if (savedInstanceState==null){

            supportFragmentManager.beginTransaction().replace(R.id.CycleContainerLayout, SelectDateFragment()).addToBackStack(null).commit()

        }


    }




}

