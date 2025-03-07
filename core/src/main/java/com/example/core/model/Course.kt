package com.example.core.model

import java.io.Serializable

data class Course(
    val id: Int,
    val summary: String,
    val createDate: String,
    val price: Double?,
    val displayPrice: String,
    val title: String,
    val cover: String,
    val owner: Int,
    val description: String,
    val canonicalUrl: String,
    var isFavorite: Boolean,
    val learnersCount: Int,
    val reviewSummary: Int,
): Serializable