package com.example.core_ui.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.ContextCompat
import com.example.core_ui.R
import com.example.core_ui.utils.DateFormatPatterns
import com.example.core_ui.utils.OfferType
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String.formatDate(
    inputPattern: String = DateFormatPatterns.ISO_8601_UTC,
    outputPattern: String = DateFormatPatterns.DISPLAY_DATE_FORMAT
): String? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val inputFormatter = DateTimeFormatter.ofPattern(inputPattern, Locale("ru"))
        val outputFormatter = DateTimeFormatter.ofPattern(outputPattern, Locale("ru"))
        val date = LocalDate.parse(this, inputFormatter)
        return date.format(outputFormatter)
    } else {
        val inputFormatter = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputFormatter = SimpleDateFormat(outputPattern, Locale.getDefault())
        val date: Date? = inputFormatter.parse(this)
        date?.let { outputFormatter.format(it) }
    }
}

fun String?.asUri(): Uri? {
    return try {
        Uri.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun Uri?.openInBrowser(context: Context) {
    this ?: return
    val browserIntent = Intent(Intent.ACTION_VIEW, this)
    ContextCompat.startActivity(context, browserIntent, null)
}