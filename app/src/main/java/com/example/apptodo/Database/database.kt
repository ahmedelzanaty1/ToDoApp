package com.example.apptodo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apptodo.Dao.Dao
import com.example.apptodo.dateconvert.Dateconvert
import com.example.apptodo.model.Model

@Database([Model::class] , version = 1)
@TypeConverters(Dateconvert::class)
abstract class database : RoomDatabase(){
    abstract fun gerdao(): Dao
    companion object{
        var instance: database? = null
        const val DATABASE_NAME = "todo"
        fun getinstance(context: Context): database {
            if(instance == null)
                instance= Room.databaseBuilder(context,database::class.java, DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            return instance!!

        }

    }

}