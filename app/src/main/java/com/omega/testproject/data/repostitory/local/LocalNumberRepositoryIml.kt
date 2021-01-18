package com.omega.testproject.data.repostitory.local

import android.content.Context
import com.omega.testproject.data.database.AppDataBase
import com.omega.testproject.data.database.DataBaseInitializer
import com.omega.testproject.data.database.NumberDBMapper.fromNum
import com.omega.testproject.data.database.NumberDBMapper.toNum

class LocalNumberRepositoryIml(context: Context) : LocalNumberRepository {
    private val database: AppDataBase = DataBaseInitializer(context).getInitDataBase()
    override suspend fun getList() =
        fromNum(database.numDao().queryUserData())


    override suspend fun updateList(list: List<String>) {
        database.numDao().insert(toNum(list))
    }
}
