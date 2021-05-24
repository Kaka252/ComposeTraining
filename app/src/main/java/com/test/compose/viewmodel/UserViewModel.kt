package com.test.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.compose.logic.Repository
import com.test.compose.model.User
import com.test.compose.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class UserViewModel(private val repository: Repository) : ViewModel() {

    val userLiveData: LiveData<Resource<List<User>>>
        get() = _userLiveData

    private val _userLiveData = MutableLiveData<Resource<List<User>>>()

    val postUiState: LiveData<Boolean>
        get() = _postUiState

    private val _postUiState = MutableLiveData<Boolean>()

    private val users = mutableListOf<User>()

    /**
     * The handler to handle coroutine exceptions and notify to the observer.
     */
    private val handler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _userLiveData.value = Resource.error(throwable.message ?: "Uncaught exception happens")
        _postUiState.value = false
    }

    /**
     * Start to read and parse the users and notify the result to the observer.
     */
    fun getUserList(index: Int) = viewModelScope.launch(handler) {
        _userLiveData.value = Resource.loading()
        if (index == 1) {
            _userLiveData.value = Resource.loading()
            _postUiState.value = true
            users.clear()
            users.addAll(repository.getUsers())
            _userLiveData.value = Resource.success(users)
            _postUiState.value = false
        } else {
            users.addAll(repository.getUsers())
        }
    }

}