package ru.sber.datetime

import java.time.*
import java.time.ZoneId.getAvailableZoneIds
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.TemporalAccessor
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> = getAvailableZoneIds()
    .asSequence()
    .filter { ZonedDateTime.now(ZoneId.of(it)).minute != ZonedDateTime
        .now(ZoneId.of("UTC")).minute }
    .toSet()

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> = Month
    .values()
    .asSequence()
    .map { LocalDateTime
        .of(year, it, 1, 0, 0)
        .with(TemporalAdjusters.lastDayOfMonth())
        .dayOfWeek
        .name }
    .toList()


// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int = Month.values()
    .asSequence()
    .filter { LocalDateTime
        .of(year, it, 13, 0, 0)
        .dayOfWeek == DayOfWeek.FRIDAY }
    .count()

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String = dateTime
    .format(DateTimeFormatter
        .ofPattern("dd MMM yyyy, HH:mm")
        .withLocale(Locale.US))



