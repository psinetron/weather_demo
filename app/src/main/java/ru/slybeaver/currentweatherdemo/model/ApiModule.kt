package ru.slybeaver.currentweatherdemo.model

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
class ApiModule {

    companion object {
        fun getApiInterface(url: String): ApiInterface {

            val httpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()

            val GSON = GsonBuilder().create()

            val builder = Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(GSON))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.client(httpClient)

            return builder.build().create(ApiInterface::class.java)
        }
    }
}