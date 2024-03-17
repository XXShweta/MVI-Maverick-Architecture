package com.tutorial.mvinewsapp.di

import com.tutorial.mvinewsapp.BuildConfig
import com.tutorial.mvinewsapp.data.remote.NewsApi
import com.tutorial.mvinewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.tutorial.mvinewsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

}
