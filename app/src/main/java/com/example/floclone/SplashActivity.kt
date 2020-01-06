package com.example.floclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    val  timeout:Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler:Handler=Handler();

        handler.postDelayed(object : Runnable {
            override fun run() {

                intent= Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()


            }


        },timeout)

    }
}
