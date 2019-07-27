package com.qucoon.watchguard.livedata

import androidx.lifecycle.MutableLiveData
import com.qucoon.rubiescircle.utils.SingleLiveEvent

class EnrollmentLiveData {
    val emailAddress = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val unquieRef = SingleLiveEvent<String>()
}