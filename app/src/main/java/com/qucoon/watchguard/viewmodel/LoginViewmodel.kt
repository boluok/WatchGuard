package com.qucoon.watchguard.viewmodel

import com.qucoon.rubiescircle.utils.SingleLiveEvent
import com.qucoon.watchguard.models.response.LoginCompleteResponse
import com.qucoon.watchguard.models.response.LoginInitResponse
import com.qucoon.watchguard.repository.LoginRepository
import com.qucoon.watchguard.utils.UseCaseResult
import com.qucoon.watchguard.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewmodel (private val loginRepository: LoginRepository):BaseViewModel(){
    val loginInitResponse = SingleLiveEvent<LoginInitResponse>()
    val loginCompleteResponse = SingleLiveEvent<LoginCompleteResponse>()


    fun loginInit() {
        showLoading.postValue(true)
        launch {
            val response = withContext(Dispatchers.IO) { loginRepository.loginInit() }
            showLoading.postValue(false)
            when (response) {
                is UseCaseResult.Success -> loginInitResponse.value = response.data
                is UseCaseResult.Failed -> showError.value = response.errorMessage
                is UseCaseResult.Error -> showError.value = Utils.handleException(response.exception)
            }
        }
    }

    fun loginInit(otp:String,pushId:String) {
        showLoading.postValue(true)
        launch {
            val response = withContext(Dispatchers.IO) { loginRepository.loginComplete(pushId, otp) }
            showLoading.postValue(false)
            when (response) {
                is UseCaseResult.Success -> loginCompleteResponse.value = response.data
                is UseCaseResult.Failed -> showError.value = response.errorMessage
                is UseCaseResult.Error -> showError.value = Utils.handleException(response.exception)
            }
        }
    }

}