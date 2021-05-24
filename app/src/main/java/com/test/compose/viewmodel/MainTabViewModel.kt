package com.test.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.compose.CourseTabs

class MainTabViewModel : ViewModel(){

    private val _position = MutableLiveData(CourseTabs.TAB_USER)
    val position: LiveData<CourseTabs> = _position

    fun onPositionChanged(position: CourseTabs) {
        _position.value = position
    }

}