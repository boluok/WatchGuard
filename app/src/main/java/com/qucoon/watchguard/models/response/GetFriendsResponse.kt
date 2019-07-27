package com.qucoon.watchguard.models.response

data class GetFriendsResponse(
    val friendlist: List<Friendlist>,
    val responsecode: String,
    val responsemessage: String
)