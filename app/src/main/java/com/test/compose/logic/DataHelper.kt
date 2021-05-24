package com.test.compose.logic

import com.google.gson.Gson
import com.test.compose.App
import com.test.compose.model.Result
import com.test.compose.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class DataHelper {

    suspend fun getUserList(): List<User> = coroutineScope {
        var users: List<User> = ArrayList()
        val job = launch(Dispatchers.IO) {
            try {
                val assetsManager = App.context.assets
                val inputReader = InputStreamReader(assetsManager.open("userData.json"))
                val jsonString = BufferedReader(inputReader).readText()
                val result = Gson().fromJson(jsonString, Result::class.java)
                users = result.userList
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        job.join()
        users
    }
}