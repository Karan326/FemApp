package com.example.floclone.DB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.floclone.profileModel

public class ViewModelClass( application: Application):AndroidViewModel(application){

      var repository:profileRepository= profileRepository(application)
    var allData:LiveData<List<profileModel>> = repository.all

    fun  addData(model: profileModel){

        repository.insertData(model)

    }

    fun fetchData():LiveData<List<profileModel>>{

        return allData

    }
    fun deleteData(){

        repository.deleteAll()


    }



}