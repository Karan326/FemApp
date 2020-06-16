package com.example.floclone

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.floclone.DB.ViewModelClass
import com.mikhaellopez.circularimageview.CircularImageView

class ProfileActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var dialog: Dialog
    lateinit var imageView: CircularImageView
    lateinit var imageUri:Uri
    lateinit var logbutton: Button

    lateinit var camera:LinearLayout
    lateinit var gallery:LinearLayout
    lateinit var delete:LinearLayout

    lateinit var viewModel: ViewModelClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        toolbar=findViewById(R.id.profileToolbar)
        dialog= Dialog(this)
        dialog.setContentView(R.layout.photo_choosing_layout)
        dialog.setCanceledOnTouchOutside(true)

        imageView=findViewById(R.id.profileImage)
        logbutton=findViewById(R.id.logoutButton)


        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        logbutton.setOnClickListener {

            deleteData()

        }


        viewModel=ViewModelProviders.of(this).get(ViewModelClass::class.java)

        viewModel.fetchData().observe(this,Observer<List<profileModel>>{t: List<profileModel>? ->

            if (t!!.isEmpty()){

                Toast.makeText(applicationContext,"No data yet here",Toast.LENGTH_SHORT).show()

            }
            else{

                val getImageAsString= t[0].toString()


                val convertToUri=Uri.parse(getImageAsString)
                Toast.makeText(applicationContext, "Pic:$getImageAsString",Toast.LENGTH_LONG).show()


            }



        })





    }
    fun setProfilePicture(view: View) {

        if (ContextCompat.checkSelfPermission(this, "android.Manifest.permission.CAMERA") != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.READ_EXTERNAL_STORAGE), 2)

        }

            dialog.show()

        camera=dialog.findViewById(R.id.chooseFromCamera)
        gallery=dialog.findViewById(R.id.chooseFromGallery)
        delete=dialog.findViewById(R.id.removePhoto)

        camera.setOnClickListener {

            val intent:Intent= Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent,1)

            dialog.dismiss()


        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode==1 && resultCode== Activity.RESULT_OK && data!=null){

            val uri=data.data


            imageUri= uri!!
            val convertedUri=uri.toString()

            Toast.makeText(applicationContext, "Updated $uri",Toast.LENGTH_LONG).show()

            val model:profileModel= profileModel(convertedUri)
            viewModel.addData(model)

            Glide.with(applicationContext).load(uri).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView)


        }
    }


    fun deleteData(){

        viewModel.deleteData()
        Toast.makeText(applicationContext,"Data Cleared",Toast.LENGTH_SHORT).show()

    }



   /* override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode==2 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {



        }

    }*/




}
