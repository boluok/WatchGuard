package com.qucoon.watchguard.repository

import com.qucoon.watchguard.livedata.EnrollmentLiveData
import com.qucoon.watchguard.localdata.pref.PaperPrefs
import com.qucoon.watchguard.models.request.EnrollmentCompleteRequest
import com.qucoon.watchguard.models.request.EnrollmentInitRequest
import com.qucoon.watchguard.models.response.EnrollmentCompleteResponse
import com.qucoon.watchguard.models.response.EnrollmentInitResponse
import com.qucoon.watchguard.remote.WatchGuardAPI
import com.qucoon.watchguard.utils.Constants
import com.qucoon.watchguard.utils.Constants.SOURCE
import com.qucoon.watchguard.utils.UseCaseResult
import kotlinx.coroutines.runBlocking

interface EnrollmentRepository {
    suspend fun enrollmentInit():UseCaseResult<EnrollmentInitResponse>
    suspend fun enrollmentComplete(otp:String):UseCaseResult<EnrollmentCompleteResponse>
}
class EnrollmentRepositoryImpl(private val watchGuardAPI: WatchGuardAPI,private val enrollmentLiveData: EnrollmentLiveData,private val paperPrefs: PaperPrefs):EnrollmentRepository{
    override suspend fun enrollmentInit(): UseCaseResult<EnrollmentInitResponse> {
        return try {
            val result = watchGuardAPI.enrollmentInit(EnrollmentInitRequest(emailaddress = enrollmentLiveData.emailAddress.value!!,firstname = enrollmentLiveData.firstName.value!!,lastname = enrollmentLiveData.lastName.value!!,phonenumber = enrollmentLiveData.phoneNumber.value!!,source =  SOURCE,uuid = paperPrefs.getDeviceUUID().await())).await()
            when(result.responsecode){
                Constants.SUCCESSCODE ->{
                    enrollmentLiveData.unquieRef.postValue(result.uniqueref)
                    UseCaseResult.Success(result)
                }
                else ->{UseCaseResult.Failed(result.responsemessage)}
            }
        }catch (ex:Exception){
            UseCaseResult.Error(ex)
        }
    }

    override suspend fun enrollmentComplete(otp:String): UseCaseResult<EnrollmentCompleteResponse> {
        return try {
            val result = watchGuardAPI.enrollmentComplete(EnrollmentCompleteRequest(otp = otp,
                source = SOURCE,pushid = paperPrefs.getPushID().await(),referallink = "test",uniqueref = enrollmentLiveData.unquieRef.value!!,
                uuid = paperPrefs.getDeviceUUID().await()
                )).await()
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