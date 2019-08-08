package com.oracle.dv.ui.login

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oracle.common.repository.LoginRepository
import com.oracle.dv.mutableLiveData
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val isReachableLiveData = mutableLiveData<Boolean>()

    fun isReachable(url: String) {
        viewModelScope.launch {
            setIsReachable(LoginRepository.doReachabilityTest(url))
        }
    }

    @MainThread
    fun getIsReacheableLiveData() = isReachableLiveData

    @MainThread
    private fun setIsReachable(isReachable: Boolean) {
        isReachableLiveData.value = isReachable
    }
}
