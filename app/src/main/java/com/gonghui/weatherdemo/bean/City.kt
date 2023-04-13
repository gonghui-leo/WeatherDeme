package com.gonghui.weatherdemo.bean

data class City(val name: String, val adcode: Int)

object CityData {
    val cities = listOf(
        City("Beijing", 110000),
        City("Shanghai", 310000),
        City("Guangzhou", 440100),
        City("Shenzhen", 440300),
        City("Suzhou", 320500),
        City("Shenyang", 210100),
    )
}
