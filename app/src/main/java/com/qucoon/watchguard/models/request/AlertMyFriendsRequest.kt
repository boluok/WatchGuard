package com.qucoon.watchguard.models.request

data class AlertMyFriendsRequest(
    val locationid: String,
    val source: String,
    val uuid: String
)