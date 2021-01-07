package com.rezadev.moviecataloguemade

import android.app.Application
import com.rezadev.core.di.databaseModule
import com.rezadev.core.di.networkModule
import com.rezadev.core.di.repositoryModule
import com.rezadev.moviecataloguemade.di.useCaseModule
import com.rezadev.moviecataloguemade.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}