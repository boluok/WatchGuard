package com.qucoon.watchguard.models.request

data class EnrollmentInitRequest(
    val emailaddress: String,
    val firstname: String,
    val lastname: String,
    val phonenumber: String,
    val source: String,
    val uuid: String
)