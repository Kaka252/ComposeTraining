package com.test.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.compose.R
import com.test.compose.model.BasicWeather
import com.test.compose.model.TwentyFourHour
import com.test.compose.model.Weather
import com.test.compose.model.WeekWeather
import com.test.compose.utils.DateUtils.getWeekListString
import com.test.compose.maintab.weather.WeatherEnum
import com.test.compose.maintab.weather.cityArray
import com.test.compose.maintab.weather.getWeatherIcon
import java.util.*

class WeatherPageViewModel : ViewModel() {

    private val _weatherLiveData = MutableLiveData<Weather>()
    val weatherLiveData: LiveData<Weather> = _weatherLiveData

    private fun onWeatherChanged(weather: Weather) {
        _weatherLiveData.value = weather
    }

    fun getWeather(): Weather {
        val random = Random()
        val city = cityArray[random.nextInt(5)]
        val weatherEnums = WeatherEnum.values()
        val weatherEnum = weatherEnums[random.nextInt(14)]
        val calendar = Calendar.getInstance()
        val hours: Int = calendar.get(Calendar.HOUR)
        val twentyFourHours = arrayListOf<TwentyFourHour>()
        val weekWeathers = arrayListOf<WeekWeather>()
        for (index in hours + 1..24) {
            twentyFourHours.add(
                TwentyFourHour(
                    "$index:00",
                    weatherEnum.icon,
                    "${random.nextInt(29)}°"
                )
            )
        }
        for (index in 1..hours) {
            twentyFourHours.add(
                TwentyFourHour(
                    "$index:00",
                    weatherEnum.icon,
                    "${random.nextInt(29)}°"
                )
            )
        }
        val week = calendar.get(Calendar.DAY_OF_WEEK)
        val weekListString = getWeekListString(week = week)
        for (index in weekListString.indices) {
            val small = random.nextInt(10)
            weekWeathers.add(
                WeekWeather(
                    weekListString[index],
                    getWeatherIcon(random.nextInt(35)), "$small°/${small + 7}°"
                )
            )
        }

        val basicWeathers = arrayListOf<BasicWeather>()
        basicWeathers.add(BasicWeather(R.string.basic_rain, "${random.nextInt(100)}%"))
        basicWeathers.add(BasicWeather(R.string.basic_humidity, "${random.nextInt(100)}%"))
        basicWeathers.add(BasicWeather(R.string.basic_body_temperature, "${random.nextInt(20)}"))
        basicWeathers.add(BasicWeather(R.string.basic_precipitation, "${random.nextInt(50)} mm"))
        basicWeathers.add(BasicWeather(R.string.basic_air_pressure, "${random.nextInt(2000)} Hpa"))
        basicWeathers.add(BasicWeather(R.string.basic_visibility, "${random.nextInt(30)} Km"))

        val weather = Weather(
            weatherEnum.weather,
            address = city,
            currentTemperature = random.nextInt(30),
            quality = random.nextInt(100),
            background = weatherEnum.background,
            backgroundGif = weatherEnum.backgroundGif,
            twentyFourHours = twentyFourHours,
            weekWeathers = weekWeathers,
            basicWeathers = basicWeathers
        )
        onWeatherChanged(weather)
        return weather
    }
}