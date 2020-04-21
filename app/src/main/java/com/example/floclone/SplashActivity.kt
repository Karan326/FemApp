package com.example.floclone

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    val  timeout:Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler:Handler=Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {

                if (getData()){

                    intent= Intent(applicationContext,UserMainScreen::class.java)
                    startActivity(intent)
                    finish()

                }else{

                    intent= Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }




            }


        },timeout)

    }

    fun  getData() : Boolean{

        val startUpPreferences: SharedPreferences =applicationContext!!.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val isItSaved :Boolean= startUpPreferences.getBoolean("saved",false)

        return isItSaved



    }




}
