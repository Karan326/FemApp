package com.example.floclone

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_tbl")
class profileModel(val image: String) {

    @PrimaryKey(autoGenerate = true)
    var id :Int=0

}