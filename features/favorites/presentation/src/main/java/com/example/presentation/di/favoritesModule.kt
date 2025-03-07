package com.example.presentation.di

import com.example.domain.di.favoritesDomainModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.presentation.ui.fragment.FavoritesViewModel

val favoritesModule = module {
    includes(favoritesDomainModule)
    viewModelOf(::FavoritesViewModel)
}