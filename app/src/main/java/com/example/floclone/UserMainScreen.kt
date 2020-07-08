package com.example.floclone

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.floclone.Content_Fragments.CalenderFragment
import com.example.floclone.Content_Fragments.InsightMenuFragment
import com.example.floclone.Content_Fragments.MoreFragment
import com.example.floclone.Content_Fragments.TodayFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker
import kotlin.system.exitProcess

class UserMainScreen : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main_screen)

        bottomNavigationView=findViewById(R.id.bottomNavigation)



        bottomNavigationView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {

                val selectedFragment: Fragment?

                when (p0.itemId) {
                    R.id.todayMenu -> {
                        selectedFragment = TodayFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.userContainer, selectedFragment!!).commit()
                        return true
                    }
                    R.id.calenderMenu -> {
                        selectedFragment = CalenderFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.userContainer, selectedFragment!!).commit()
                        return true
                    }
                    R.id.insightMenu -> {
                        selectedFragment = InsightMenuFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.userContainer, selectedFragment!!).commit()
                        return true
                    }
                    R.id.moreMenu -> {
                        selectedFragment = MoreFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.userContainer, selectedFragment!!).commit()
                        return true
                    }

                }

                return true
            }

            })


        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.userContainer, TodayFragment()).commit()
        }



}

    override fun onBackPressed() {


      if (getData()){


      }else{

          intent= Intent(applicationContext,MainActivity::class.java)
          startActivity(intent)
          finish()

      }

        super.onBackPressed()
    }

    fun  getData() : Boolean{

        val startUpPreferences: SharedPreferences =applicationContext!!.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val isItSaved :Boolean= startUpPreferences.getBoolean("saved",false)

        return isItSaved



    }

}
