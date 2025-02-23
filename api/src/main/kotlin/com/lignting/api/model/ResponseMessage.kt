package com.lignting.api.model

import org.springframework.http.HttpStatus

data class ResponseMessage<T>(
    val code: Int,
    val message: String,
    val data: T,
)

fun <T> T.success() = ResponseMessage(
    HttpStatus.OK.value(),
    "success!",
    this
)

fun <T> T.failed(message: String) = ResponseMessage(
    HttpStatus.NOT_FOUND.value(),
    message,
    this
)