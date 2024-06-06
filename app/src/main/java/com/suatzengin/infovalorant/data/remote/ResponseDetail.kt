package com.suatzengin.infovalorant.data.remote

data class ResponseDetail<T>(
    val status: Int,
    val data: T
)
