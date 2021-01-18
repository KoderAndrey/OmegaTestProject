package com.omega.testproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "num")
public class Num(
    @PrimaryKey
    @ColumnInfo(name = "numValue")
    val num: String
)