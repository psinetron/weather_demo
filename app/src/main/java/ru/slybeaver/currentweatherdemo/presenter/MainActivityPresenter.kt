package ru.slybeaver.currentweatherdemo.presenter

import android.location.Location
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.slybeaver.currentweatherdemo.dto.WeatherResponseDTO
import ru.slybeaver.currentweatherdemo.model.ModelApiImpl
import ru.slybeaver.currentweatherdemo.ui.view.BaseView
import ru.slybeaver.currentweatherdemo.ui.view.MainActivityView

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
class MainActivityPresenter(var view:MainActivityView):BasePresenter() {

    override fun getView(): BaseView {
        return view
    }

    /**
     * Загружаем погоду по текущему местоположению
     */
    fun getWeather(location: Location, listener:(WeatherResponseDTO) -> Unit) {
        showLoadingState()
        val subscriber:Disposable = ModelApiImpl().getWeather(location.latitude,location.longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            hideLoadingState()
                            if (it.cod==200) {
                                listener(it)
                            } else {
                                view.noConnectionError(Runnable { getWeather(location, listener) }, true)
                            }
                        },
                        {
                            hideLoadingState()
                            view.noConnectionError(Runnable { getWeather(location, listener) }, true)
                        }
                )
        addDisposable(subscriber)
    }

    /**
     * Загружаем погоду по названию города
     */
    fun getWeather(city:String, listener: (WeatherResponseDTO) -> Unit) {
        showLoadingState()
        val subscriber:Disposable = ModelApiImpl().getWeather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            hideLoadingState()
                            if (it.cod == 200) {
                                listener(it)
                            } else {
                                view.noConnectionError(Runnable { getWeather(city, listener) }, true)
                            }
                        },
                        {
                            hideLoadingState()
                            view.noConnectionError(Runnable { getWeather(city, listener) }, true)
                        }
                )
        addDisposable(subscriber)
    }

}