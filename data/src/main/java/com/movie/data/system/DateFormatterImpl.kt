package com.movie.data.system

import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import com.movie.domain.entities.Date
import com.movie.domain.system.DateFormatter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.temporal.ChronoField
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap

//should be Singleton
class DateFormatterImpl(private val context: Context) : DateFormatter {

    init {
        AndroidThreeTen.init(context)
    }

    private val parseCache = ConcurrentHashMap<String, DateTimeFormatter>()
    private val formatCache = ConcurrentHashMap<String, DateTimeFormatter>()

    override fun parse(value: String, pattern: String, locale: Locale): Date {
        val date = LocalDateTime.parse(value, getOrCreateParseFormatter(pattern, locale))
        return Date(
            date.year,
            date.monthValue,
            date.dayOfMonth,
            date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        )
    }

    override fun format(value: Date, pattern: String, locale: Locale): String {
        val date = value.run {
            LocalDate.of(year, month, dayOfMonth)
        }
        return date.format(getOrCreateFormatFormatter(pattern, locale))
    }

    private fun getOrCreateParseFormatter(pattern: String, locale: Locale): DateTimeFormatter {
        return parseCache.getOrPut(pattern) {
            DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                .toFormatter(locale)

        }
    }

    private fun getOrCreateFormatFormatter(pattern: String, locale: Locale): DateTimeFormatter {
        return formatCache.getOrPut(pattern) {
            DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .toFormatter(locale)
        }
    }
}