package com.remotely.io.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("ID")
    val ID: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("profilePicture")
    val profilePicture: String?,
    @SerializedName("location")
    val location: String?
) : Parcelable