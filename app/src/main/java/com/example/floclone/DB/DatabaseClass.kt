package com.example.floclone.DB

import android.content.Context
import android.os.AsyncTask
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.floclone.profileModel

@Database(entities = [profileModel::class],version = 1)
abstract class  DatabaseClass : RoomDatabase() {


    abstract fun  dao():profileDao

    companion object{

        var instance:DatabaseClass?=null

        fun getInstance(context: Context):DatabaseClass?{

            if (instance==null){

                synchronized(DatabaseClass::class){

                    instance=Room.databaseBuilder(context.applicationContext,DatabaseClass::class.java,"profileDb").fallbackToDestructiveMigration().addCallback(
                        roomCallback).build()

                }

            }
            return instance
        }

        private val roomCallback = object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }

    }


}

class PopulateDbAsyncTask(instance: DatabaseClass?):AsyncTask<profileModel,Unit,Unit>() {

    val db=instance!!.dao()

    override fun doInBackground(vararg params: profileModel?) {


    }


}
