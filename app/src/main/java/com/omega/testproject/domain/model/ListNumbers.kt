package com.omega.testproject.domain.model

import com.google.gson.annotations.SerializedName

data class ListNumbers(
    @SerializedName("Numbers")
    val numbers: List<NumberElement>?
)