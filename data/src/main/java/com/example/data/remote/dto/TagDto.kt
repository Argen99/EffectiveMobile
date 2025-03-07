package com.example.data.remote.dto

import com.example.data.core.utils.DataMapper
import com.example.domain.model.Tag

data class TagDto(
    val id: Int,
    val title: String,
): DataMapper<Tag> {
    override fun toDomain() = Tag(
        id = id,
        title = title,
    )
}