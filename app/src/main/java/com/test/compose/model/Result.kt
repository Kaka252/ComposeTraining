package com.test.compose.model

import android.os.Parcelable
import com.test.compose.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
class Result(
    val userList: List<User>
) : Parcelable