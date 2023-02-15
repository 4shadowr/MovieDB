package com.example.moviedb.data

data class NetworkResult<T>(
    val isSuccess: Boolean,
    val data: T?,
    val message: String? = null
) {
}