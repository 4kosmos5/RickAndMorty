package com.example.rickandmortysemyon.di

import com.example.rickandmortysemyon.data.repository.CharacterRepository
import com.example.rickandmortysemyon.data.network.CharacterService
import com.example.rickandmortysemyon.ui.screen.character.CharacterViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    fun provideCharacterApi(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }
    single { provideCharacterApi(get()) }

}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }

}

val repositoryModule = module {

    fun provideCharacterRepository(api: CharacterService): CharacterRepository {
        return CharacterRepository(api)
    }
    single { provideCharacterRepository(get()) }

}

val viewModelModule = module {

    viewModel { CharacterViewModel(get()) }

}