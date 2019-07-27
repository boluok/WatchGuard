package com.qucoon.watchguard.models.request

data class LoginCompleteRequest(
    val otp: String,
    val pushid: String,
    val referallink: String,
    val source: String,
    val uuid: String
)