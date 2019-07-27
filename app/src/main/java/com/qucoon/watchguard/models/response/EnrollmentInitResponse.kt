package com.qucoon.watchguard.models.response

data class EnrollmentInitResponse(
    val emailaddress: String,
    val firstname: String,
    val lastname: String,
    val phonenumber: String,
    val responsecode: String,
    val responsemessage: String,
    val source: String,
    val uniqueref: String,
    val uuid: String
)