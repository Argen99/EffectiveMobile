package com.example.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.presentation.ui.fragment.AccountViewModel

val accountModule = module {
    viewModelOf(::AccountViewModel)
}