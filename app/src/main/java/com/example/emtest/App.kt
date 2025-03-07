package com.example.emtest

import android.app.Application
import com.example.data.di.dataModule
import com.example.presentation.di.accountModule
import com.example.presentation.di.mainModule
import com.example.presentation.di.favoritesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                mainModule,
                favoritesModule,
                accountModule,
                dataModule
            )
        }
    }
}