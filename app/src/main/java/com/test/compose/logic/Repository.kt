package com.test.compose.logic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dataHelper: DataHelper) {

    suspend fun getUsers() = withContext(Dispatchers.IO) {
        dataHelper.getUserList()
    }
}