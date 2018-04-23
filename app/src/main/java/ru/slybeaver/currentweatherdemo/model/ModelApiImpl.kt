package ru.slybeaver.currentweatherdemo.model

import io.reactivex.Single
import ru.slybeaver.currentweatherdemo.dto.WeatherResponseDTO
import ru.slybeaver.currentweatherdemo.utils.AppSettings

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
class ModelApiImpl: ModelApi {

    private val apiInterface = ApiModule.getApiInterface(AppSettings.instance.API_ADDRESS)

    override fun getWeather(lat: Double, lng: Double): Single<WeatherResponseDTO> {
        return apiInterface.getWeather(lat,lng,"metric", "ru", AppSettings.instance.API_KEY)
    }

    override fun getWeather(city: String): Single<WeatherResponseDTO> {
        return apiInterface.getWeather(city,"metric", "ru", AppSettings.instance.API_KEY)
    }
}