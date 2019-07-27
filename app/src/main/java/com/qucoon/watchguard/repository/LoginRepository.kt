package com.qucoon.watchguard.repository

import com.qucoon.watchguard.localdata.pref.PaperPrefs
import com.qucoon.watchguard.models.request.LoginCompleteRequest
import com.qucoon.watchguard.models.request.LoginInitRequest
import com.qucoon.watchguard.models.response.LoginCompleteResponse
import com.qucoon.watchguard.models.response.LoginInitResponse
import com.qucoon.watchguard.remote.WatchGuardAPI
import com.qucoon.watchguard.utils.Constants
import com.qucoon.watchguard.utils.UseCaseResult

interface LoginRepository {
    suspend fun loginInit():UseCaseResult<LoginInitResponse>
    suspend fun loginComplete(pushId:String,otp:String):UseCaseResult<LoginCompleteResponse>
}
class LoginRepositoryImpl(private val watchGuardAPI: WatchGuardAPI,private val paperPrefs: PaperPrefs):LoginRepository{
    override suspend fun loginInit(): UseCaseResult<LoginInitResponse> {
        return try {
            val result = watchGuardAPI.loginInit(LoginInitRequest(source = Constants.SOURCE,uuid = paperPrefs.getDeviceUUID().await())).await()
            when(result.responsecode){
                Constants.SUCCESSCODE ->{
                    UseCaseResult.Success(result)
                }
                else ->{UseCaseResult.Failed(result.responsemessage)}
            }
        }catch (ex:Exception){
            UseCaseResult.Error(ex)
        }
    }

    override suspend fun loginComplete(pushId:String,otp:String): UseCaseResult<LoginCompleteResponse> {
        return try {
            val result = watchGuardAPI.loginComplete(LoginCompleteRequest(source = Constants.SOURCE,uuid = paperPrefs.getDeviceUUID().await(),pushid = pushId,otp = otp,referallink = "test")).await()
            when(result.responsecode){
                Constants.SUCCESSCODE ->{
                    UseCaseResult.Success(result)
                }
                else ->{UseCaseResult.Failed(result.responsemessage)}
            }
        }catch (ex:Exception){
            UseCaseResult.Error(ex)
        }
    }

}