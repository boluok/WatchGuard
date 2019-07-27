package com.qucoon.watchguard.models.request

data class CustomerStatusRequest(
    val pushid: String,
    val referallink: String,
    val source: String,
    val uuid: String
)