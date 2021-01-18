package com.omega.testproject.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.omega.testproject.domain.model.ListNumbers
import com.omega.testproject.domain.usecase.LocalWorkUseCase
import com.omega.testproject.domain.usecase.NumberListUseCase
import com.omega.testproject.presentation.ResultType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


class NumberViewModel(
    private val numberUseCase: NumberListUseCase,
    private val localWorkUseCase: LocalWorkUseCase
) : ViewModel(), KoinComponent {

    companion object {
        const val EMPTY_LIST = "Empty list of values"
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val resultData: LiveEvent<Pair<ResultType, String>> = LiveEvent()
    private val addItem: LiveEvent<Any> = LiveEvent()
    private val deleteItem: LiveEvent<Any> = LiveEvent()
    private val calculateEvent: LiveEvent<Any> = LiveEvent()
    val isLoad: MutableLiveData<Boolean> = MutableLiveData(false)
    val numList: MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun initList() {
        viewModelScope.launch {
            localWorkUseCase.getList().run {
                when (this.isEmpty()) {
                    true -> numList.postValue(arrayListOf("", ""))
                    false -> numList.postValue(ArrayList(this))
                }

            }
        }
    }

    fun addItem() {
        addItem.postValue(Any())
    }

    fun executeCalculate() {
        calculateEvent.postValue(Any())
    }

    fun deleteItem() {
        deleteItem.postValue(Any())
    }

    fun getSum(listNumbers: List<String>) {
        viewModelScope.launch {
            try {
                isLoad.postValue(true)
                listNumbers.filter { value ->
                    value.trim().isNotEmpty()
                }.let { list ->
                    when (list.isNotEmpty()) {
                        true -> {
                            numberUseCase.getNum(list)?.run {
                                resultData.postValue(Pair(ResultType.SUCCESS, this))
                            }
                            localWorkUseCase.saveList(list)
                        }
                        false -> {
                            resultData.postValue(Pair(ResultType.ERROR, EMPTY_LIST))
                        }
                    }
                }
            } catch (e: Exception) {
                e.message?.let { errorMessage ->
                    resultData.postValue(Pair(ResultType.ERROR, errorMessage))
                }
            } finally {
                isLoad.postValue(false)
            }
        }
    }

    fun result() = resultData
    fun addItemEvent() = addItem
    fun deleteItemEvent() = deleteItem
    fun calculateEvent() = calculateEvent
}