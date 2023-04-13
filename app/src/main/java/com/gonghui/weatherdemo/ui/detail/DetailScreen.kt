package com.gonghui.weatherdemo.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gonghui.weatherdemo.bean.WeatherData

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    weatherCode: Int?
) {
    val weatherData = viewModel.getWeather(weatherCode ?: 100000).collectAsState(
        initial = null
    )

    // Because the state is read here,
    // DetailScreen recomposes whenever dataExample changes.
    weatherData.value?.let {
        WeatherItem(it.forecasts?.first())
    }
}

@Composable
fun WeatherItem(
    weatherData: WeatherData.Forecast?
) {
    Column {
        Text(text = weatherData?.city ?: "")
        weatherData?.casts?.forEach { cast ->
            Column {
                Text(text = "date:${cast?.date}")
                Text(text = "dayweather:${cast?.dayweather}")
                Text(text = "daywind:${cast?.daywind}")
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}