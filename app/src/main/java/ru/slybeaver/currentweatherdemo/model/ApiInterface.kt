package ru.slybeaver.currentweatherdemo.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.slybeaver.currentweatherdemo.dto.WeatherResponseDTO

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
interface ApiInterface {

    @GET("weather")
    fun getWeather(@Query("lat") lat:Double,
                   @Query("lon") lon:Double,
                   @Query("units") units:String,
                   @Query("lang") lang:String,
                   @Query("appid") appid:String): Single<WeatherResponseDTO>

    @GET("weather")
    fun getWeather(@Query("q") city:String,
                   @Query("units") units:String,
                   @Query("lang") lang:String,
                   @Query("appid") appid:String): Single<WeatherResponseDTO>
}