package com.test.compose.model

import androidx.annotation.DrawableRes
import com.test.compose.R

data class Weather(
    val weather: Int = R.string.weather_sunny,
    val address: Int = R.string.city_new_york,
    val currentTemperature: Int = 0,
    val quality: Int = 0,
    @DrawableRes val background: Int = R.drawable.home_bg_1,
    @DrawableRes val backgroundGif: Int = R.drawable.bg_topgif_2,
    val twentyFourHours: List<TwentyFourHour> = arrayListOf(),
    val weekWeathers: List<WeekWeather> = arrayListOf(),
    val basicWeathers: List<BasicWeather> = arrayListOf()
)

data class TwentyFourHour(
    val time: String = "",
    @DrawableRes val icon: Int,
    val temperature: String
)

data class WeekWeather(
    val weekStr: String = "",
    @DrawableRes val icon: Int,
    val temperature: String = ""
)

data class BasicWeather(
    val name: Int,
    val value: String = ""
)