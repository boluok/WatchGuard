package com.qucoon.watchguard.models.response

data class LoginCompleteResponse(
    val emailaddress: String,
    val firstname: String,
    val lastlogindate: String,
    val lastname: String,
    val mobilenumber: String,
    val profilestatus: String,
    val responsecode: String,
    val responsemessage: String,
    val uuid: String
)