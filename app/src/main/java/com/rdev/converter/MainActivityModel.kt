package com.rdev.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class MainActivityModel : ViewModel() {
    private val resultLiveData: MutableLiveData<Result> = MutableLiveData<Result>()

    fun calculate(data: String) {
        val result = Result(null)
        try {
            val parsed = data.toFloat()
            if (parsed < 1 || parsed > 1000) {
                result.error = "Value must be in range 1-1000"
            }
            val temp = 60 / parsed
            result.min = temp.toInt()
            result.sec = ((temp - result.min) * 60).roundToInt()
        } catch (e: Exception) {
            result.error = "Value must be number in range 1-1000"
        }
        resultLiveData.postValue(result)
    }

    fun getResult(): LiveData<Result> {
        return resultLiveData
    }
}