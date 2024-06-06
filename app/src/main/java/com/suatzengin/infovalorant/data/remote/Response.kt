package com.suatzengin.infovalorant.data.remote

data class Response<T>(
    val status: Int,
    val data: List<T>
)
