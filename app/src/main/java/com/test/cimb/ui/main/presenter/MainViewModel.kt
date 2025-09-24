package com.test.cimb.ui.main.presenter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.cimb.model.User
import com.test.cimb.repository.UserRepository
import com.test.cimb.util.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    private val _userList = MutableLiveData(emptyList<User>())
    val userList: LiveData<List<User>> = _userList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData("")
    val error: LiveData<String> = _error

    fun getUsers(context: Context) {
        viewModelScope.launch {
            if (NetworkUtil.isInternetAvailable(context)) {
                _isLoading.value = true
                _userList.value = repository.getUsers()
                _isLoading.value = false
                _error.value = ""
            } else {
                _error.value = "No Internet Available"
            }
        }
    }

}