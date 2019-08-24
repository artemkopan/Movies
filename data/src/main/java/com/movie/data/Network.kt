package com.movie.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.movie.data.network.service.ApiService
import com.movie.domain.common.Logger
import com.movie.domain.system.SystemConfig
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

val network = module {
    single { createGson() }
    single { createOkHttpClient() }

    factory { createWebService<ApiService>() }
}


inline fun <reified T> Scope.createWebService(): T {
    return com.movie.data.createWebService((get() as SystemConfig).apiUrl, get(), get())
}


inline fun <reified T> createWebService(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): T {
    val retrofit = baseRetrofitBuilder(baseUrl, okHttpClient, gson)
        .build()
    return retrofit.create(T::class.java)
}

fun baseRetrofitBuilder(url: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .callbackExecutor(retrofitExecutor)
        .addConverterFactory(GsonConverterFactory.create(gson))
}

fun Scope.createGson(): Gson {
    return GsonBuilder().create()
}

private val retrofitExecutor by lazy { Executors.newSingleThreadExecutor() }


private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .callTimeout(5, TimeUnit.MINUTES)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(4, 1L, TimeUnit.MINUTES))
        .addNetworkInterceptor(
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Logger.d("Okhttp", it) })
                .setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                )
        )
        .build()
}
