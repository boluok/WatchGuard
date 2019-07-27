package com.qucoon.watchguard.models.request

data class UpdateProfileRequest(
    val firstname: String,
    val lastname: String,
    val mobilenumber: String,
    val source: String,
    val uuid: String
)