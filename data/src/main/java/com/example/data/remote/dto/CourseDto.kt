package com.example.data.remote.dto

import com.example.core.model.Course
import com.example.data.core.utils.DataMapper
import com.google.gson.annotations.SerializedName

data class CourseDto(
    val id: Int,
    val summary: String,
    @SerializedName("create_date")
    val createDate: String,
    val price: Double?,
    @SerializedName("display_price")
    val displayPrice: String,
    val title: String,
    val cover: String,
    val owner: Int,
    val description: String,
    @SerializedName("canonical_url")
    val canonicalUrl: String,
    @SerializedName("learners_count")
    val learnersCount: Int,
    @SerializedName("review_summary")
    val reviewSummary: Int,
): DataMapper<Course> {

    override fun toDomain() = Course(
        id = id,
        summary = summary,
        createDate = createDate,
        price = price,
        displayPrice = displayPrice,
        title = title,
        cover = cover,
        owner = owner,
        description = description,
        canonicalUrl = canonicalUrl,
        isFavorite = false,
        learnersCount = learnersCount,
        reviewSummary = reviewSummary
    )
}