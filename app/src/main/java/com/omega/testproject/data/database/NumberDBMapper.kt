package com.omega.testproject.data.database

import com.omega.testproject.data.Num

object NumberDBMapper {
    infix fun fromNum(from: List<Num>)=
        from.map { it.num }

    infix fun toNum(from: List<String>) =
        from.map { Num(it) }
}