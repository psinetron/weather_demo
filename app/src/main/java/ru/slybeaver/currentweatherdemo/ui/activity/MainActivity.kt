package ru.slybeaver.currentweatherdemo.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import ru.slybeaver.currentweatherdemo.R
import ru.slybeaver.currentweatherdemo.dto.WeatherResponseDTO
import ru.slybeaver.currentweatherdemo.presenter.MainActivityPresenter
import ru.slybeaver.currentweatherdemo.presenter.Presenter
import ru.slybeaver.currentweatherdemo.utils.AppSettings
import ru.slybeaver.currentweatherdemo.ui.view.MainActivityView

class MainActivity : BaseActivity(), MainActivityView {
    /**
     * Предоставленое апи имеет возможность выводить данные за интервалы времени, однако требует платного доступа
     * в связи с чем не реализовано в рамках ТЗ
     */

    private val presenter = MainActivityPresenter(this)
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val PERMISSION_LOCATION: Int = 1
    private var REQUEST_SENDED:Boolean = false

    override fun getPresenter(): Presenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtCityName.setOnClickListener { openActivity(CitiesActivity::class.java) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_LOCATION -> {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (AppSettings.instance.currentCity == null) {
                        refreshLocation()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        AppSettings.instance.currentCity?.let {
            txtCityName.text = it
            presenter.getWeather(it, {
                showMainData(it)
            })
        }?: run {
            txtCityName.text = getString(R.string.current_location)
            if (!REQUEST_SENDED) {
                refreshLocation()
            } else if (REQUEST_SENDED && (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                            ActivityCompat.checkSelfPermission(this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                AppSettings.instance.currentCity = AppSettings.instance.ALLCITIES[0]
                txtCityName.text = AppSettings.instance.ALLCITIES[0]
                presenter.getWeather(AppSettings.instance.ALLCITIES[0], {
                    showMainData(it)
                })
            }
        }

    }

    /**
     * Refresh current location to load actually weather info
     */
    @SuppressLint("MissingPermission")
    private fun refreshLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (!hasPermissions()) {
            REQUEST_SENDED = true
            getPermission(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSION_LOCATION)
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    if (AppSettings.instance.currentCity == null) {
                        presenter.getWeather(it, {
                            showMainData(it)
                        })
                    }
                }
            }
        }
    }

    /**
     * Show weather
     * @param data - Loaded info about weather
     */
    private fun showMainData(data: WeatherResponseDTO) {
        data.main?.temp?.let {
            if (it > 0) {
                txtMainWeather.text = getString(R.string.positive_weather, Math.round(it).toString())
            } else {
                txtMainWeather.text = getString(R.string.negative_weather, Math.round(it).toString())
            }
        }
        data.weather?.get(0)?.let { Picasso.with(this@MainActivity.applicationContext).load("${AppSettings.instance.API_ICON_ADDRESS}${it.icon}.png").into(imgWeather) }
    }

    private fun hasPermissions():Boolean {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }


}
