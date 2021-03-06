package com.test.compose.maintab.weather

import com.test.compose.R

fun getWeatherIcon(weatherId: Int): Int {
    when (weatherId) {
        WeatherType.SUNNY -> return R.drawable.n_weather_icon_sunny
        WeatherType.CLOUDY -> return R.drawable.n_weather_icon_cloud
        WeatherType.OVERCAST -> return R.drawable.n_weather_icon_overcast
        WeatherType.SHOWER -> return R.drawable.n_weather_icon_shower
        WeatherType.THUNDER_SHOWER -> return R.drawable.n_weather_icon_thunder_rain
        WeatherType.THUNDER_SHOWER_WITH_HAIL -> return R.drawable.n_weather_icon_rain_ice
        WeatherType.RAIN_SNOW -> return R.drawable.n_weather_icon_rain_snow
        WeatherType.SMALL_RAIN, WeatherType.SMALL_TO_MIDRAIN -> return R.drawable.n_weather_icon_small_rain
        WeatherType.MID_RAIN, WeatherType.MID_TO_BIGRAIN -> return R.drawable.n_weather_icon_middle_rain
        WeatherType.BIG_RAIN, WeatherType.BIG_TO_STORMRAIN -> return R.drawable.n_weather_icon_big_rain
        WeatherType.RAINSTORM, WeatherType.DOWNPOUR, WeatherType.TORREN_RAIN, WeatherType.STORM_TO_HEAVY_STORM,
        WeatherType.HEAVY_TO_SEVERE_STORM -> return R.drawable.n_weather_icon_storm
        WeatherType.SNOW_FLURRY -> return R.drawable.n_weather_icon_flurry
        WeatherType.SMALL_SNOW, WeatherType.LIGHT_TO_MODERATE_SNOW -> return R.drawable.n_weather_icon_light_snow
        WeatherType.MID_SNOW, WeatherType.MODERATE_TO_HEAVY_SNOW -> return R.drawable.n_weather_icon_moderate_snow
        WeatherType.BIG_SNOW, WeatherType.HEAVY_SNOW_TO_SNOWSTORM -> return R.drawable.n_weather_icon_heavy_snow
        WeatherType.SNOWSTORM -> return R.drawable.n_weather_icon_snow_storm
        WeatherType.FOGGY -> return R.drawable.n_weather_icon_fog
        WeatherType.ICE_RAIN -> return R.drawable.n_weather_icon_rain_ice
        WeatherType.SANDSTORM -> return R.drawable.n_weather_icon_sand_storm
        WeatherType.DUST -> return R.drawable.n_weather_icon_dust
        WeatherType.JANSA -> return R.drawable.n_weather_icon_sand
        WeatherType.STRONG_SANDSTORM -> return R.drawable.n_weather_icon_sand_storm
        WeatherType.HAZE -> return R.drawable.n_weather_icon_haze
        WeatherType.NA -> return R.drawable.n_weather_icon_na
    }
    return R.drawable.n_weather_icon_na
}

val cityArray = arrayListOf(
    R.string.city_new_york,
    R.string.city_los_angeles,
    R.string.city_chicago,
    R.string.city_houston,
    R.string.city_san_francisco,
)

object WeatherType {

    /**
     * error
     */
    const val NA = 99

    /**
     * ??????
     */
    const val SUNNY = 0

    /**
     * ??????
     */
    const val CLOUDY = 1

    /**
     * ??????
     */
    const val OVERCAST = 2

    /**
     * ??????
     */
    const val SHOWER = 3

    /**
     * ?????????
     */
    const val THUNDER_SHOWER = 4

    /**
     * ????????????
     */
    const val THUNDER_SHOWER_WITH_HAIL = 5

    /**
     * ??????
     */
    const val RAIN_SNOW = 6

    /**
     * ??????
     */
    const val SMALL_RAIN = 7

    /**
     * ??????
     */
    const val MID_RAIN = 8

    /**
     * ??????
     */
    const val BIG_RAIN = 9

    /**
     * ??????
     */
    const val RAINSTORM = 10

    /**
     * ?????????
     */
    const val DOWNPOUR = 11

    /**
     * ???
     */
    const val TORREN_RAIN = 12

    /**
     * ??????
     */
    const val SNOW_FLURRY = 13

    /**
     * ??????
     */
    const val SMALL_SNOW = 14

    /**
     * ??????
     */
    const val MID_SNOW = 15

    /**
     * ??????
     */
    const val BIG_SNOW = 16

    /**
     * ?????????
     */
    const val SNOWSTORM = 17

    /**
     * ??????
     */
    const val FOGGY = 18

    /**
     * ??????
     */
    const val ICE_RAIN = 19

    /**
     * ??????
     */
    const val SANDSTORM = 20

    /**
     * ????????????
     */
    const val SMALL_TO_MIDRAIN = 21

    /**
     * ????????????
     */
    const val MID_TO_BIGRAIN = 22

    /**
     * ???????????????
     */
    const val BIG_TO_STORMRAIN = 23

    /**
     * ????????????????????????
     */
    const val STORM_TO_HEAVY_STORM = 24

    /**
     * ???????????????
     */
    const val HEAVY_TO_SEVERE_STORM = 25

    /**
     * ????????????
     */
    const val LIGHT_TO_MODERATE_SNOW = 26

    /**
     * ??????????????????
     */
    const val MODERATE_TO_HEAVY_SNOW = 27

    /**
     * ?????????
     */
    const val HEAVY_SNOW_TO_SNOWSTORM = 28

    /**
     * ??????
     */
    const val DUST = 29

    /**
     * ????????
     */
    const val JANSA = 30

    /**
     * ????????????
     */
    const val STRONG_SANDSTORM = 31

    /**
     * ??????
     */
    // const val ICE = 32

    /**
     * ??????
     */
    const val HAZE = 53
}