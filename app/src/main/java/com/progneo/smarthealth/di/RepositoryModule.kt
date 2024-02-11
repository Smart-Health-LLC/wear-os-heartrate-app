package com.progneo.smarthealth.di

import com.progneo.smarthealth.data.repository.HealthServicesRepository
import com.progneo.smarthealth.data.repository.HealthServicesRepositoryImpl
import com.progneo.smarthealth.data.repository.PassiveDataRepository
import com.progneo.smarthealth.data.repository.PassiveDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindHealthServicesRepository(
        healthServicesRepositoryImpl: HealthServicesRepositoryImpl
    ): HealthServicesRepository

    @Binds
    @Singleton
    internal abstract fun bindPassiveDataRepository(
        passiveDataRepositoryImpl: PassiveDataRepositoryImpl
    ): PassiveDataRepository
}
