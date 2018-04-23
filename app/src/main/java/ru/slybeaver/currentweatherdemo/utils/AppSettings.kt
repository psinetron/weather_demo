package ru.slybeaver.currentweatherdemo.utils

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
class AppSettings private constructor(){

    private object Holder {
        val INSTANCE = AppSettings()
    }

    companion object {
        val instance:AppSettings by lazy {Holder.INSTANCE}
    }

    val API_KEY = "3b4c154c7183602ecfb036482ba3bf99"
    val API_ADDRESS = "http://api.openweathermap.org/data/2.5/"
    val API_ICON_ADDRESS = "http://openweathermap.org/img/w/"

    var currentCity:String? = null

    var ALLCITIES = arrayListOf<String>("London", "Paris", "Tokyo", "New York")

}