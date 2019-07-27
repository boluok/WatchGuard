package com.qucoon.watchguard.models.response

data class UpdateProfileResponse(
    val firstname: String,
    val lastname: String,
    val mobilenumber: String,
    val pushid: String,
    val referallink: String,
    val responsecode: String,
    val responsemessage: String,
    val source: String,
    val uuid: String
)