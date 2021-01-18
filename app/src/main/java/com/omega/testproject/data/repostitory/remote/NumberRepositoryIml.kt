package com.omega.testproject.data.repostitory.remote

import com.omega.testproject.data.NumberService
import com.omega.testproject.data.apimodel.SumResult
import com.omega.testproject.domain.model.ListNumbers

class NumberRepositoryIml(private val api: NumberService) : NumberRepository {

    override suspend fun getSum(list: ListNumbers): SumResult = api.getSum(list)
}