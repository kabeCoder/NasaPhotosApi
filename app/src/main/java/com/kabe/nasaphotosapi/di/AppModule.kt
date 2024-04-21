package com.kabe.nasaphotosapi.di

import android.content.Context
import androidx.room.Room
import com.kabe.nasaphotosapi.BuildConfig.DEBUG
import com.kabe.nasaphotosapi.constants.AppConstants
import com.kabe.nasaphotosapi.data.base.RetrofitBuilder
import com.kabe.nasaphotosapi.data.database.AppDatabase
import com.kabe.nasaphotosapi.data.network.NasaPhotoService
import com.kabe.nasaphotosapi.data.respository.NasaPhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIMEOUT = 20000 //20 seconds
    private const val DATABASE_NAME = "nasa_photos_database"

    // Retrofit Builder
    @Provides
    @Singleton
    fun provideClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {

            connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)

            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            })

            addInterceptor(RetrofitBuilder.NoInternetInterceptor(appContext))

        }.build()
    }

    @Provides
    @Singleton
    fun createRetrofitInstance(client: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(AppConstants.BASE_URL)
        client(client)
    }.build()

    @Provides
    @Singleton
    fun provideRandomPersonService(retrofit: Retrofit): NasaPhotoService {
        return retrofit.create(NasaPhotoService::class.java)
    }

    @Provides
    @Singleton
    fun provideRandomPersonRepository(
        nasaPhotoService: NasaPhotoService,
        appDatabase: AppDatabase
    ): NasaPhotoRepository {
        return NasaPhotoRepository(nasaPhotoService, appDatabase)
    }

    @Provides
    @Singleton
    fun provideNasaPersonDb(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}