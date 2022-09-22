package com.example.mohamedelsayedsalemandroidtask.di

import com.example.mohamedelsayedsalemandroidtask.network.MealApi
import com.example.mohamedelsayedsalemandroidtask.network.SSL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



   @Provides
    @Singleton
    fun provideApi() : MealApi =
        Retrofit.Builder()
            .baseUrl("https://satatechnologygroup.net:3301/api/")
            .client(SSL().unsafeOkHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)


}