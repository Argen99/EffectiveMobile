package com.example.data.core.utils

interface DataMapper<T> {
    fun toDomain(): T
}