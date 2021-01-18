package com.omega.testproject.domain.model

import com.google.gson.annotations.SerializedName

data class NumberElement(
    @SerializedName("Number")
    val number: String?
)