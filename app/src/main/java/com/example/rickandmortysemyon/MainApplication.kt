package com.example.rickandmortysemyon

import android.app.Application
import com.example.rickandmortysemyon.di.apiModule
import com.example.rickandmortysemyon.di.repositoryModule
import com.example.rickandmortysemyon.di.retrofitModule
import com.example.rickandmortysemyon.di.viewModelModule
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