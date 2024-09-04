package com.example.apptodo.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.apptodo.model.Model
import java.util.Date

@Dao
interface Dao {
    @Insert
    fun inserttask(task: Model)
    @Delete
    fun deletetask(task: Model)
    @Update
    fun updatetask(task: Model)
    @Query("SELECT * FROM todo")
    fun getalltask(): List<Model>
    @Query("SELECT * FROM todo WHERE id = :id")
    fun gettaskbyid(id: Date): List<Model>







}