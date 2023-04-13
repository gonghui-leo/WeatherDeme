package com.gonghui.weatherdemo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Net {
    val api by lazy {
        val httpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)//失败重试一次
            .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时
            .readTimeout(10, TimeUnit.SECONDS)//读取超时
            .writeTimeout(10, TimeUnit.SECONDS)//写入超时
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder().baseUrl("https://restapi.amap.com/v3/weather/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(WeatherApi::class.java)
    }
}