package com.example.domain.model

data class Tag(
    val id: Int,
    val title: String,
    val isChecked: Boolean = false,
)