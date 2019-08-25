package io.movies

import android.app.Application
import android.util.Log
import com.movie.data.data
import com.movie.data.mappers
import com.movie.data.network
import com.movie.domain.common.Logger
import com.movie.domain.domain
import com.movie.presentation.presentation
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.MESSAGE
import timber.log.Timber

typealias KoinLogger = org.koin.core.logger.Logger

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()

        Timber.plant(Timber.DebugTree())
        Logger.addPrinter(TimberPrinter())
    }

    private fun initDi() {
        startKoin {
            logger(object : KoinLogger() {
                override fun log(level: Level, msg: MESSAGE) {
                    when (level) {
                        Level.DEBUG -> Logger.d("koin", msg)
                        Level.INFO -> Logger.i("koin", msg)
                        Level.ERROR -> Logger.e("koin", msg)
                    }
                }
            })
            androidContext(this@MoviesApplication)
            modules(
                listOf(
                    data, network, mappers, domain, presentation
                )
            )
        }
    }

    private class TimberPrinter : Logger.Printer {

        override fun log(
            @Logger.Priority priority: Int, tag: String,
            message: String,
            t: Throwable?
        ) {
            when (priority) {
                Logger.DEBUG -> timber.log.Timber.tag(tag).log(Log.DEBUG, t, message)
                Logger.ERROR -> timber.log.Timber.tag(tag).log(Log.ERROR, t, message)
                Logger.INFO -> timber.log.Timber.tag(tag).log(Log.INFO, t, message)
                Logger.WARN -> timber.log.Timber.tag(tag).log(Log.WARN, t, message)
                else -> timber.log.Timber.tag(tag).log(priority, t, message)
            }
        }
    }

}