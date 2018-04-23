package ru.slybeaver.currentweatherdemo.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_select_city.*
import ru.slybeaver.currentweatherdemo.R
import ru.slybeaver.currentweatherdemo.presenter.Presenter
import ru.slybeaver.currentweatherdemo.ui.adapters.CityListAdapter
import ru.slybeaver.currentweatherdemo.utils.AppSettings

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
class CitiesActivity:BaseActivity() {
    override fun getPresenter(): Presenter? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_city)
        recyclerCities.layoutManager = LinearLayoutManager(this)
        recyclerCities.setHasFixedSize(true)
        recyclerCities.adapter = CityListAdapter(AppSettings.instance.ALLCITIES,{
            AppSettings.instance.currentCity = it
            finish()
        })
    }

}