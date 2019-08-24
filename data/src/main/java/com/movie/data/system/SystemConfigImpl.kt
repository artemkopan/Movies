package com.movie.data.system

import android.content.Context
import android.os.Build
import com.movie.domain.system.SystemConfig

class SystemConfigImpl(private val context: Context) : SystemConfig {

    override val apiUrl: String
        get() = "https://api.themoviedb.org/3/"

    override val deviceLanguage: String
        get() = context.resources.configuration.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                locales[0]
            } else {
                locale
            }
        }.language

}