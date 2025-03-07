package com.example.presentation.di

import com.example.domain.di.mainDomainModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.presentation.ui.fragment.MainViewModel
import com.example.presentation.ui.fragment.course_info.CourseInfoViewModel

val mainModule = module {
    includes(mainDomainModule)
    viewModelOf(::MainViewModel)
    viewModelOf(::CourseInfoViewModel)
}