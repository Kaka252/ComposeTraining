package com.test.compose.maintab.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.compose.model.Weather
import com.test.compose.model.WeekWeather

@Composable
fun WeatherWeek(weather: Weather) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .padding(horizontal = 10.dp)
    ) {
        for (weekWeather in weather.weekWeathers) {
            WeatherWeekDetails(weekWeather)
        }
    }
}

@Composable
fun WeatherWeekDetails(weekWeather: WeekWeather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {
        Text(
            weekWeather.weekStr,
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .weight(1f),
            color = Color.White, fontSize = 15.sp
        )
        Image(
            modifier = Modifier
                .size(25.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .weight(1f),
            painter = painterResource(id = weekWeather.icon),
            contentDescription = weekWeather.temperature
        )
        Text(
            weekWeather.temperature,
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .weight(1f),
            color = Color.White, fontSize = 15.sp
        )
    }
}