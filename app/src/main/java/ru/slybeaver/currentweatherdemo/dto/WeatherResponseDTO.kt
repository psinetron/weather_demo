package ru.slybeaver.currentweatherdemo.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
class WeatherResponseDTO {

    @SerializedName("coord") var coord:CoordsDTO? = null
    @SerializedName("weather") var weather:ArrayList<WeatherDTO>? = ArrayList()
    @SerializedName("base") var base:String? = null
    @SerializedName("main") var main:MainDTO? = null
    @SerializedName("visibility") var visibility:Float? = null
    @SerializedName("cod") var cod:Int? = null


    class CoordsDTO {
        @SerializedName("lat") var lat:Double? = null
        @SerializedName("lon") var lng:Double? = null
    }

    class WeatherDTO {
        @SerializedName("id") var id:Int? = null
        @SerializedName("main") var main:String? = null
        @SerializedName("description") var description:String? = null
        @SerializedName("icon") var icon:String? = null
    }

    class MainDTO {
        @SerializedName("temp") var temp:Float? = null
        @SerializedName("pressure") var pressure:Float? = null
        @SerializedName("humidity") var humidity:Float? = null
        @SerializedName("temp_min") var tempMin:Float? = null
        @SerializedName("temp_max") var tempMax:Float? = null
    }

    class WindDTO {
        @SerializedName("speed") var speed:Float?= null
        @SerializedName("deg") var deg:Float? = null
    }

    //TODO Если уложусь в 3 часа - можно рассмотреть остальные параметры для вывода


}