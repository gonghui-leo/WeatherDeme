package com.gonghui.weatherdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.gonghui.weatherdemo.ui.detail.DetailScreen
import com.gonghui.weatherdemo.ui.detail.DetailViewModel
import com.gonghui.weatherdemo.ui.home.HomeViewModel
import com.gonghui.weatherdemo.ui.home.HomeScreen
import com.gonghui.weatherdemo.ui.theme.WeatherDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            Row {
                Text(
                    text = "Weather Demo", style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = HomePage.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = HomePage.route) {
                val viewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(viewModel) { adcode ->
                    navController.navigate("${WeatherDetail.route}/$adcode")
                }
            }
            composable(route = "${WeatherDetail.route}/{weather_code}",
                arguments = listOf(
                    navArgument("weather_code") { type = NavType.IntType }
                ),
                deepLinks = listOf(
                    navDeepLink { uriPattern = "weather://${WeatherDetail.route}/{weather_code}" }
                )) { navBackStackEntry ->
                val viewModel = hiltViewModel<DetailViewModel>()
                val weatherCode =
                    navBackStackEntry.arguments?.getInt("weather_code")
                DetailScreen(viewModel, weatherCode)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherDemoTheme {
        WeatherApp()
    }
}