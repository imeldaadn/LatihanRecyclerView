package com.imeldaaudina.latihanrecyclerview
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Handphone(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable


