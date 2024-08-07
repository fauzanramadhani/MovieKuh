package com.ndc.moviekuh.data.module

import android.content.Context
import androidx.room.Room
import com.ndc.moviekuh.BuildConfig
import com.ndc.moviekuh.data.source.local.room.MovieKuhDatabase
import com.ndc.moviekuh.data.source.local.sharedpref.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object MovieKuhModule {
    @Provides
    fun provideSharedPref(
        @ApplicationContext context: Context,
    ) = SharedPreferencesManager(context)

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MovieKuhDatabase::class.java, "db_moviekuh"
    ).build()

    @Provides
    fun provideRetrofit() : Retrofit {
        val authorizationInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
                .build()
            chain.proceed(newRequest)
        }

        val createOkHttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(authorizationInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttp)
            .build()
    }
}