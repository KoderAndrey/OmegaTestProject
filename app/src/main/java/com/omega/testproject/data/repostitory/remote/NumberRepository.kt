package com.omega.testproject.data.repostitory.remote

import com.omega.testproject.data.apimodel.SumResult
import com.omega.testproject.domain.model.ListNumbers
import org.koin.core.KoinComponent

interface NumberRepository: KoinComponent {

    suspend fun getSum(list: ListNumbers): SumResult
}