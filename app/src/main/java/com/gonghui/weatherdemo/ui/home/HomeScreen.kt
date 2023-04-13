package com.gonghui.weatherdemo.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.gonghui.weatherdemo.bean.City
import com.gonghui.weatherdemo.bean.CityData
import com.gonghui.weatherdemo.HomePage

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onClick: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = HomePage.route }
    ) {
        CityItem(CityData.cities, onClick)
    }
}

@Composable
fun CityItem(
    cities: List<City>,
    onClick: (Int) -> Unit = {}
) {
    cities.forEach { city ->
        TextButton(onClick = { onClick(city.adcode) }) {
            Text(text = city.name)
        }
    }
}