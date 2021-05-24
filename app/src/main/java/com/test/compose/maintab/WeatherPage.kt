package com.test.compose.maintab

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.compose.maintab.weather.*
import com.test.compose.model.Weather
import com.test.compose.ui.theme.MyApplicationTheme
import com.test.compose.utils.ProgressIndicator
import com.test.compose.utils.REFRESH_START
import com.test.compose.utils.REFRESH_STOP
import com.test.compose.utils.SwipeToRefreshLayout
import com.test.compose.viewmodel.WeatherPageViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "WeatherPage"

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherPage() {
    val coroutineScope = rememberCoroutineScope()
    val refreshingState = remember { mutableStateOf(REFRESH_STOP) }
    val weatherPageViewModel: WeatherPageViewModel = viewModel()
    val weather by weatherPageViewModel.weatherLiveData.observeAsState(Weather())
    var loadState by remember { mutableStateOf(false) }
    if (!loadState) {
        loadState = true
        weatherPageViewModel.getWeather()
    }

    Surface(color = MaterialTheme.colors.background) {
        SwipeToRefreshLayout(
            refreshingState = refreshingState.value,
            onRefresh = {
                refreshingState.value = REFRESH_START
                Log.e(TAG, "WeatherPage: refresh")
                coroutineScope.launch {
                    delay(1000)
                    weatherPageViewModel.getWeather()
                    loadState = true
                    refreshingState.value = REFRESH_STOP
                }
            },
            progressIndicator = {
                ProgressIndicator()
            }
        ) {
            WeatherBackground(weather)
            WeatherContent(weather)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun WeatherContent(weather: Weather) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState),
    ) {
        WeatherBasic(weather, scrollState)
        WeatherDetails(weather)
        WeatherWeek(weather)
        WeatherOther(weather)
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyApplicationTheme() {
        WeatherPage()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyApplicationTheme(darkTheme = true) {
        WeatherPage()
    }
}