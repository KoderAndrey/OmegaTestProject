package com.omega.testproject.domain.usecase

import com.omega.testproject.data.repostitory.remote.NumberRepository
import com.omega.testproject.domain.model.ListNumbers
import com.omega.testproject.domain.model.NumberElement


class NumberListUseCase(private val userRepository: NumberRepository) {

    suspend fun getNum(list: List<String>): String? {
        return list.map { num ->
            NumberElement(num)
        }.toMutableList().let { numElements ->
            userRepository.getSum(ListNumbers(numElements)).sum
        }
    }
}

