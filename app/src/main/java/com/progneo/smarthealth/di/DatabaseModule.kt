package com.progneo.smarthealth.di

import android.content.Context
import androidx.room.Room
import com.progneo.smarthealth.data.api.service.HeartRateService
import com.progneo.smarthealth.data.cache.db.AppDatabase
import com.progneo.smarthealth.data.cache.db.HeartRateDao
import com.progneo.smarthealth.data.repository.HeartRateCacheRepositoryImpl
import com.progneo.smarthealth.data.repository.HeartRateRemoteRepositoryImpl
import com.progneo.smarthealth.domain.repository.HeartRateCacheRepository
import com.progneo.smarthealth.domain.repository.HeartRateRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideHeartRateDao(database: AppDatabase): HeartRateDao =
        database.heartTateDao()

    @Provides
    @Singleton
    fun provideHeartRateCacheRepository(
        heartRateDao: HeartRateDao
    ): HeartRateCacheRepository = HeartRateCacheRepositoryImpl(heartRateDao)

    @Provides
    @Singleton
    fun provideHeartRateRemoteRepository(
        heartRateService: HeartRateService
    ): HeartRateRemoteRepository = HeartRateRemoteRepositoryImpl(heartRateService)
}
