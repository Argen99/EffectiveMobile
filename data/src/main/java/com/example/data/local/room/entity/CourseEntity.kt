package com.example.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.Course
import com.example.data.core.utils.DataMapper

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey
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
        isFavorite = true,
        0,
        0
    )
}

fun Course.toEntity() = CourseEntity(
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
)