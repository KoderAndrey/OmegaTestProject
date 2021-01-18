package com.omega.testproject.data.repostitory.local

import org.koin.core.KoinComponent

interface LocalNumberRepository : KoinComponent {
    suspend fun getList(): List<String>

    suspend fun updateList(list: List<String>)
}
