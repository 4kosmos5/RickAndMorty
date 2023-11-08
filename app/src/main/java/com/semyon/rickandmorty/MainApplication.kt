package com.semyon.rickandmorty

import android.app.Application
import com.semyon.rickandmorty.di.apiModule
import com.semyon.rickandmorty.di.repositoryModule
import com.semyon.rickandmorty.di.retrofitModule
import com.semyon.rickandmorty.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        startKoin {
            androidContext(this@MainApplication)
            modules(apiModule,
                viewModelModule,
                repositoryModule,
                retrofitModule)
        }
    }
}