package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import com.example.domain.use_case.GetUserUseCase
import com.example.domain.use_case.GetCoursesUseCase
import com.example.domain.use_case.GetTagsUseCase
import com.example.domain.use_case.OnFavoritesClickUseCase
import com.example.domain.use_case.GetCourseByIdUseCase
import org.koin.dsl.module

val mainDomainModule = module {
    factoryOf(::GetUserUseCase)
    factoryOf(::GetCoursesUseCase)
    factoryOf(::GetTagsUseCase)
    factoryOf(::OnFavoritesClickUseCase)
    factoryOf(::GetCourseByIdUseCase)
}