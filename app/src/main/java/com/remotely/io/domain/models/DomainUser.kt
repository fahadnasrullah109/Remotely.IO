package com.remotely.io.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainUser(
    val name: String,
    val profilePicture: String,
    val location: String,
) : Parcelable