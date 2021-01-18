package com.omega.testproject.data.database

import android.content.Context
import androidx.room.Room

class DataBaseInitializer(context: Context) {

    private val database: AppDataBase = Room.databaseBuilder(
        context.applicationContext,
        AppDataBase::class.java, DATABASE_NAME
    ).build()

    fun getInitDataBase() = database

    companion object {
        private const val DATABASE_NAME = "omega.db"
    }
}