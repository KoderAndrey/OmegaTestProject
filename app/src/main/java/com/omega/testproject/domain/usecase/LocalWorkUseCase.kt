package com.omega.testproject.domain.usecase

import com.omega.testproject.data.repostitory.local.LocalNumberRepository


class LocalWorkUseCase(private val localRepository: LocalNumberRepository) {


    suspend fun getList() =
        localRepository.getList()

    suspend fun saveList(list: List<String>) {
        localRepository.updateList(list)
    }
}

