package com.example.floclone.DB

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.floclone.profileModel

class profileRepository(application: Application) {

    lateinit var dao:profileDao
    lateinit var all:LiveData<List<profileModel>>


    init {
        val databaseClass:DatabaseClass= DatabaseClass.getInstance(application)!!

        dao=databaseClass.dao()
        all=dao.fetchData()


    }

    fun insertData(model: profileModel){

        val addNew=addNewDataAsyncTask(dao).execute(model)

    }

    private class addNewDataAsyncTask(dao: profileDao): AsyncTask<profileModel,Unit,Unit>() {

        val mydao=dao

        override fun doInBackground(vararg params: profileModel?) {

            mydao.insertNew(params[0]!!)
        }


    }


    fun deleteAll(){

        val delete=deleteAsyncTask(dao).execute()

    }

    fun fetchData():LiveData<List<profileModel>>{

        return all
    }

}

class deleteAsyncTask(dao: profileDao):AsyncTask<Unit,Unit,Unit>() {

    val mydao=dao
    override fun doInBackground(vararg params: Unit?) {
        mydao.deleteAll()
    }


}
