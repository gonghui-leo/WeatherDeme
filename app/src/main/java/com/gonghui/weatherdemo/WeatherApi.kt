package com.gonghui.weatherdemo

import com.gonghui.weatherdemo.bean.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weatherInfo?key=44603b97d6e9c6978c411b743921d7b0&extensions=all")
    suspend fun getWeather(@Query("city") adcode: Int): WeatherData
}