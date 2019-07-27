package com.qucoon.watchguard.models.response

data class EnrollmentCompleteResponse(
    val otp: String,
    val pushid: String,
    val referallink: String,
    val responsecode: String,
    val responsemessage: String,
    val source: String,
    val uniqueref: String,
    val uuid: String
)