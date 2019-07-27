package com.qucoon.watchguard.models.response

data class AlertMyFriendsResponse(
    val responsecode: String,
    val responsemessage: String,
    val result: List<Result>
)