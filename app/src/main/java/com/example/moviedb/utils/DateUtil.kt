package com.example.moviedb.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getFormattedDate(
        dateString: String,
        sourceFormat: String,
        targetFormat: String
    ) : String {
        val inputFormat = SimpleDateFormat(sourceFormat, Locale.getDefault())
        val outputFormat = SimpleDateFormat(targetFormat, Locale.getDefault())
        val formatDate: Date = inputFormat.parse(dateString)

        return outputFormat.format(formatDate)
    }
}