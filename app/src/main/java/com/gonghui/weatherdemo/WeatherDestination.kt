package com.gonghui.weatherdemo

interface WeatherDestination {
    val route: String
}

object HomePage : WeatherDestination {
    override val route = "home"
}

object WeatherDetail : WeatherDestination {
    override val route = "detail"
}