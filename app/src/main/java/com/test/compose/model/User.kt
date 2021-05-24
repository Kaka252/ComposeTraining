package com.test.compose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    val geekAvatar: String,
    val geekName: String,
    val geekContent: String,
    val geekGender : Int,
    val geekWorkYear: String,
    val geekDegree : String,
    val salary : String,
    val subTitle: String
) : Parcelable