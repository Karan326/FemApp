package com.example.floclone.Content_Fragments


import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.example.floclone.ProfileActivity

import com.example.floclone.R
import com.example.floclone.ReportsActivity
import com.example.floclone.SettingsActivity


class MoreFragment : Fragment() {

    lateinit var imageview:ImageView
    lateinit var linearLayout: LinearLayout
    lateinit var reminderCardView: CardView
    lateinit var profileCardView: CardView
    lateinit var settingCardView: CardView
    lateinit var reportsCardView: CardView
    lateinit var dialog: Dialog
    lateinit var ovulation:Switch
    lateinit var tips:Switch
    lateinit var period:Switch
    var notifyCheck:Boolean=false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View=inflater.inflate(R.layout.fragment_more, container, false)

        dialog= Dialog(container!!.context)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(R.layout.reminder_settings_layout)


        reminderCardView=view.findViewById(R.id.notificationCard)
        profileCardView=view.findViewById(R.id.profileCard)
        settingCardView=view.findViewById(R.id.settingsCard)
        reportsCardView=view.findViewById(R.id.reportsCard)

        profileCardView.setOnClickListener {

            val intent:Intent= Intent(context,ProfileActivity::class.java)
            startActivity(intent)


        }

        settingCardView.setOnClickListener {


            val intent:Intent= Intent(context,SettingsActivity::class.java)
            startActivity(intent)

        }

        reportsCardView.setOnClickListener {


            val intent:Intent= Intent(context,ReportsActivity::class.java)
            startActivity(intent)

        }

        reminderCardView.setOnClickListener {


            dialog.show()

            ovulation=dialog.findViewById(R.id.ovulationSwitch)
            tips=dialog.findViewById(R.id.tipsSwitch)
            period=dialog.findViewById(R.id.periodSwitch)

            //Values At Default
            ovulation.isChecked=notifyCheck
            tips.isChecked=notifyCheck
            period.isChecked=notifyCheck

            //Ovulation Switch
            if (checkOvulationValue()){

                ovulation.isChecked=true

            }
            //Ovulation Switch
            if (checkTipsValue()){

                tips.isChecked=true

            }
            //Period Switch
            if (checkPeriodValue()){

                period.isChecked=true

            }

            val buttonSave:Button=dialog.findViewById(R.id.saveReminder)
            buttonSave.setOnClickListener {

                //Ovulation Switch
              if (ovulation.isChecked){

                  saveOvulationValue()

              }else {
                  removeOvulationValues()

              }
                //Tips Switch
                if (tips.isChecked){
                    saveTipsValue()
                }else {
                    removeTipsValues()

                }
                //Period Switch
                if (period.isChecked){
                    savePeriodValue()
                }else {
                    removePeriodValues()

                }


                dialog.dismiss()

            }


        }




        return view
    }


    //Reminder Settings
    fun saveTipsValue(){
        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("tipsReminder",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.putBoolean("isItOn",true)
        editor.apply()

    }
    fun savePeriodValue(){
        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("periodReminder",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.putBoolean("isItOn",true)
        editor.apply()

    }
    fun saveOvulationValue(){
        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("ovulationReminder",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.putBoolean("isItOn",true)
        editor.apply()

    }

    private fun checkTipsValue() :Boolean {

        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("tipsReminder",MODE_PRIVATE)
       val saved= sharedPreferences.getBoolean("isItOn",false)

        return saved

    }
    private fun checkPeriodValue() :Boolean {

        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("periodReminder",MODE_PRIVATE)
        val saved= sharedPreferences.getBoolean("isItOn",false)

        return saved

    }
    private fun checkOvulationValue() :Boolean {

        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("ovulationReminder",MODE_PRIVATE)
        val saved= sharedPreferences.getBoolean("isItOn",false)

        return saved

    }

    fun removeTipsValues(){

        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("tipsReminder",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.remove("isItOn")
        editor.apply()


    }
    fun removePeriodValues(){

        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("periodReminder",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.remove("isItOn")
        editor.apply()


    }
    fun removeOvulationValues(){

        val sharedPreferences:SharedPreferences=context!!.getSharedPreferences("ovulationReminder",MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.remove("isItOn")
        editor.apply()


    }


}
