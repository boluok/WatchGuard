package com.qucoon.watchguard.models.request

data class EnrollmentCompleteRequest(
    val otp: String,
    val pushid: String,
    val referallink: String,
    val source: String,
    val uniqueref: String,
    val uuid: String
)