package com.progneo.smarthealth.di

import com.progneo.smarthealth.data.api.service.HeartRateSocketService
import com.progneo.smarthealth.data.api.socket.WebSocketManager
import com.progneo.smarthealth.data.api.socket.WebSocketManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.net.URI
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {

    @Provides
    @Singleton
    @Named("SocketUrl")
    fun provideSocketUrl() = "wss://socketsbay.com/wss/v2/1/demo/" // TODO: paste url

    @Provides
    @Singleton
    fun provideWebSocketManager(
        @Named("SocketUrl") url: String
    ): WebSocketManager = WebSocketManagerImpl(URI(url))

    @Provides
    @Singleton
    fun provideHeartRateSocketService(
        webSocketManager: WebSocketManager
    ): HeartRateSocketService = HeartRateSocketService(webSocketManager)
}
