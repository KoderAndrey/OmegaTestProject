package com.omega.testproject.data

import com.omega.testproject.data.apimodel.SumResult
import com.omega.testproject.domain.model.ListNumbers
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NumberService {
    @POST("sum")
    suspend fun getSum(@Body list: ListNumbers): SumResult
}