package com.example.newscompose.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity()
data class Article(
    val author: String? = null,
    val content: String = "",
    val description: String? = null,
    val publishedAt: String = "",
    val source: Source = Source("",""),
    val title: String = "",
    @PrimaryKey val url: String = "",
    val urlToImage: String? = null,
    var saveStatus:Boolean?=false
):Parcelable