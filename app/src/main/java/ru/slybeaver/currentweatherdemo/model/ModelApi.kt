package ru.slybeaver.currentweatherdemo.model

import io.reactivex.Single
import ru.slybeaver.currentweatherdemo.dto.WeatherResponseDTO

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
interface ModelApi {

    fun getWeather(lat:Double, lng:Double): Single<WeatherResponseDTO>

    fun getWeather(city:String): Single<WeatherResponseDTO>

}