package com.example.domain.use_case

import com.example.domain.repository.MainRepository

class GetUserUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(userId: Int) = repository.getUser(userId)
}