package com.example.data.remote.dto

data class TagsResponseDto(
    val meta: MetaDto,
    val tags: List<TagDto>,
)