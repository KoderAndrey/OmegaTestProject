package com.omega.testproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omega.testproject.data.Num


@Dao
interface NumDao {

    @Query("SELECT * FROM num")
    suspend fun queryUserData(): List<Num>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nums: List<Num>)
}