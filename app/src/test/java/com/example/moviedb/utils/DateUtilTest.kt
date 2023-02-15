package com.example.moviedb.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DateUtilTest {

    @Test
    fun `Valid Date Formatting`() {
        val result = DateUtil.getFormattedDate(
            "2023-02-14",
            "yyyy-MM-dd",
            "MMM, dd yyyy"
        )
        assertThat(result).matches("Feb, 14 2023")
    }


    @Test
    fun `Valid Month Formatting`() {
        val result = DateUtil.getFormattedDate(
            "2023-02-14",
            "yyyy-MM-dd",
            "MMM, dd yyyy"
        )
        assertThat(result).doesNotMatch("February, 14 2023")
    }
}