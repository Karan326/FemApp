package com.example.floclone.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.floclone.profileModel

@Dao
interface profileDao {

    @Insert
    fun insertNew(model:profileModel)
    @Query("SELECT * FROM profile_tbl")
    fun fetchData():LiveData<List<profileModel>>
    @Query("DELETE FROM profile_tbl")
    fun deleteAll()



}