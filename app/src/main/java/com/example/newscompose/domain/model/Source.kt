package com.example.newscompose.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val id: String? = null,
    val name: String = ""
):Parcelable