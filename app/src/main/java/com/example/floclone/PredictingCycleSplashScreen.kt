package com.example.floclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class PredictingCycleSplashScreen : AppCompatActivity() {

    var timeout:Long=4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_predicting_cycle_splash_screen)


        val handle:Handler= Handler()
        handle.postDelayed(object : Runnable {

            override fun run() {

                intent= Intent(applicationContext,UserMainScreen::class.java)
                startActivity(intent)
                finish()

            }


        },timeout)


    }
}
