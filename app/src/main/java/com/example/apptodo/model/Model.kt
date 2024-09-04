package com.example.apptodo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo")
data class Model(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title:String?= null,
    var description:String?= null,
    var date : Date?= null,
    var isDone: Boolean = false,
    var Time:String?=null
)
