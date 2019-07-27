package com.qucoon.watchguard.viewmodel

import com.qucoon.rubiescircle.utils.SingleLiveEvent
import com.qucoon.watchguard.models.response.EnrollmentCompleteResponse
import com.qucoon.watchguard.models.response.EnrollmentInitResponse
import com.qucoon.watchguard.repository.EnrollmentRepository
import com.qucoon.watchguard.utils.UseCaseResult
import com.qucoon.watchguard.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnrollmentViewmodel (private val enrollmentRepository: EnrollmentRepository):BaseViewModel(){
    val enrollmentInitResponse = SingleLiveEvent<EnrollmentInitResponse>()
    val enrollmentComplete = SingleLiveEvent<EnrollmentCompleteResponse>()


    fun enrollmentInit() {
        showLoading.postValue(true)
        launch {
            val response = withContext(Dispatchers.IO) { enrollmentRepository.enrollmentInit() }
            showLoading.postValue(false)
            when (response) {
                is UseCaseResult.Success -> enrollmentInitResponse.value = response.data
                is UseCaseResult.Failed -> showError.value = response.errorMessage
                is UseCaseResult.Error -> showError.value = Utils.handleException(response.exception)
            }
        }
    }

    fun enrollmentComplete(otp:String) {
        showLoading.postValue(true)
        launch {
            val response = withContext(Dispatchers.IO) { enrollmentRepository.enrollmentComplete(otp) }
            showLoading.postValue(false)
            when (response) {
                is UseCaseResult.Success -> enrollmentComplete.value = response.data
                is UseCaseResult.Failed -> showError.value = response.errorMessage
                is UseCaseResult.Error -> showError.value = Utils.handleException(response.exception)
            }
        }
    }
}