package com.example.data.remote.dto

import com.example.data.core.utils.DataMapper
import com.example.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Int,
    val avatar: String,
    @SerializedName("full_name")
    val fullName: String,
): DataMapper<User> {

    override fun toDomain() = User(
        id = id,
        avatar = avatar,
        fullName = fullName,
    )
}