package com.gonghui.weatherdemo.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gonghui.weatherdemo.Net
import com.gonghui.weatherdemo.bean.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    val weatherData = MutableLiveData<WeatherData?>()
    fun getWeather(weatherCode: Int) {
        Net.api.getWeather(weatherCode).enqueue(object :Callback<WeatherData>{
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                weatherData.postValue(response.body())
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                weatherData.postValue(null)
            }
        })
    }
}