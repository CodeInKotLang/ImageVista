package com.example.imagevista.di

import android.content.Context
import androidx.room.Room
import com.example.imagevista.data.local.ImageVistaDatabase
import com.example.imagevista.data.remote.UnsplashApiService
import com.example.imagevista.data.repository.AndroidImageDownloader
import com.example.imagevista.data.repository.ImageRepositoryImpl
import com.example.imagevista.data.repository.NetworkConnectivityObserverImpl
import com.example.imagevista.data.util.Constants
import com.example.imagevista.data.util.Constants.IMAGE_VISTA_DATABASE
import com.example.imagevista.domain.repository.Downloader
import com.example.imagevista.domain.repository.ImageRepository
import com.example.imagevista.domain.repository.NetworkConnectivityObserver
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUnsplashApiService(): UnsplashApiService {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        val retrofit = Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(UnsplashApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageVistaDatabase(
        @ApplicationContext context: Context
    ): ImageVistaDatabase {
        return Room
            .databaseBuilder(
                context,
                ImageVistaDatabase::class.java,
                IMAGE_VISTA_DATABASE
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideImageRepository(
        apiService: UnsplashApiService,
        database: ImageVistaDatabase
    ): ImageRepository {
        return ImageRepositoryImpl(apiService, database)
    }

    @Provides
    @Singleton
    fun provideAndroidImageDownloader(
        @ApplicationContext context: Context
    ): Downloader {
        return AndroidImageDownloader(context)
    }

    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityObserver(
        @ApplicationContext context: Context,
        scope: CoroutineScope
    ): NetworkConnectivityObserver {
        return NetworkConnectivityObserverImpl(context, scope)
    }
}