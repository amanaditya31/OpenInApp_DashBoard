package com.example.openinapp.di

import com.example.openinapp.di.AppModule.API_KEY
import com.example.openinapp.network.AppApi
import com.example.openinapp.network.OAuthInterceptor
import com.example.openinapp.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl.Companion.get
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol.Companion.get
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.IOException
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent:: class)
object AppModule{

    const val API_KEY="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi
        .Builder()
        .run {
            add(KotlinJsonAdapterFactory())
                .build()
        }

    @Provides
    @Singleton
    fun provideAppApi(moshi: Moshi, okHttpClient: OkHttpClient): AppApi =
            Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(AppApi::class.java)

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(
            OAuthInterceptor(
                "Bearer",
                API_KEY,
            )
        )
            .addInterceptor(interceptor)
            .build()
    }

//    @Singleton
//    @Provides
//    fun provideAppApi(): AppApi{
//                val client = OkHttpClient.Builder()
//                    .addInterceptor(ServiceInterceptor())
//                    //.readTimeout(45,TimeUnit.SECONDS)
//                    //.writeTimeout(45,TimeUnit.SECONDS)
//                    .build()
//
//                return Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(AppApi::class.java)
//        }

}