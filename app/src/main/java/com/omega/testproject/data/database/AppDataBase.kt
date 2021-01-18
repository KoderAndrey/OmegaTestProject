package com.omega.testproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omega.testproject.BuildConfig
import com.omega.testproject.data.Num

@Database(entities = [Num::class], version = BuildConfig.DATABASE_VERSION, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun numDao(): NumDao
}