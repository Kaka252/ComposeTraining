package com.test.compose.maintab.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.compose.model.TwentyFourHour
import com.test.compose.model.Weather

@Composable
fun WeatherDetails(weather: Weather) {
    val twentyFourHours = weather.twentyFourHours
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(twentyFourHours) { twentyFourHour ->
            WeatherHour(twentyFourHour)
        }
    }
}

@Composable
fun WeatherHour(twentyFourHour: TwentyFourHour) {
    val modifier = Modifier.padding(top = 9.dp)
    Column(modifier = Modifier.width(50.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(modifier = modifier, text = twentyFourHour.time, color = Color.White, fontSize = 15.sp)
        Image(
            modifier = modifier.size(25.dp),
            painter = painterResource(id = twentyFourHour.icon),
            contentDescription = twentyFourHour.temperature
        )
        Text(
            modifier = modifier,
            text = twentyFourHour.temperature,
            color = Color.White,
            fontSize = 15.sp
        )
    }
}