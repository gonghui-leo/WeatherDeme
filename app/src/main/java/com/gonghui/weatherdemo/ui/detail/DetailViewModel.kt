package com.gonghui.weatherdemo.ui.detail

import androidx.lifecycle.ViewModel
import com.gonghui.weatherdemo.Net
import kotlinx.coroutines.flow.flow

class DetailViewModel : ViewModel() {
    fun getWeather(weatherCode: Int) = flow {
        emit(Net.api.getWeather(weatherCode))
    }

}