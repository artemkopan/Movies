package com.movie.domain.system

import com.movie.domain.entities.Date
import java.util.Locale

interface DateFormatter {

    fun parse(value: String, pattern: String, locale: Locale): Date

    fun format(value: Date, pattern: String, locale: Locale): String

}