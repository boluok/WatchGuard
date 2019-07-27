package com.qucoon.watchguard.remote

import com.qucoon.watchguard.models.request.*
import com.qucoon.watchguard.models.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface WatchGuardAPI {
    @POST("enrolmentinitiate")
    fun enrollmentInit(@Body params: EnrollmentInitRequest): Deferred<EnrollmentInitResponse>

    @POST("enrolmentcomplete")
    fun enrollmentComplete(@Body params: EnrollmentCompleteRequest): Deferred<EnrollmentCompleteResponse>

    @POST("login")
    fun loginInit(@Body params: LoginInitRequest): Deferred<LoginInitResponse>

    @POST("logincomplete")
    fun loginComplete(@Body params: LoginCompleteRequest): Deferred<LoginCompleteResponse>

    @POST("getfriends")
    fun getFriendsList(@Body params: GetFriendsRequest): Deferred<GetFriendsResponse>

    @POST("updateprofile")
    fun updateProfile(@Body params: UpdateProfileRequest): Deferred<UpdateProfileResponse>

    @POST("customerstatus")
    fun customerStatus(@Body params: CustomerStatusRequest): Deferred<CustomerStatusResponse>

    @POST("alertmyfriends")
    fun alertMyFriends(@Body params: AlertMyFriendsRequest): Deferred<AlertMyFriendsResponse>
}