package ru.slybeaver.currentweatherdemo

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .build())

        Stetho.initializeWithDefaults(this)
    }
}