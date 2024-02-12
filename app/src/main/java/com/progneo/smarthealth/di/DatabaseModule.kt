package com.progneo.smarthealth.di

import android.content.Context
import androidx.room.Room
import com.progneo.smarthealth.dao.db.AppDatabase
import com.progneo.smarthealth.dao.db.HeartRateDao
import com.progneo.smarthealth.dao.mapper.HeartRateMapper
import com.progneo.smarthealth.dao.source.HeartRateCacheDatasourceImpl
import com.progneo.smarthealth.data.datasource.HeartRateCacheDatasource
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
    fun provideHeartRateCacheDatasource(
        heartRateDao: HeartRateDao,
        heartRateMapper: HeartRateMapper
    ): HeartRateCacheDatasource = HeartRateCacheDatasourceImpl(heartRateDao, heartRateMapper)
}
