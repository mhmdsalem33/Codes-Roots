package com.example.mohamedelsayedsalemandroidtask.network

import com.example.mohamedelsayedsalemandroidtask.data.BannerAds.BannerList
import com.example.mohamedelsayedsalemandroidtask.data.BannerAds.GoogleId
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.BestSallerList
import com.example.mohamedelsayedsalemandroidtask.data.CategoriesData.CategoriesList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface MealApi {


    @Headers("Lang:en" , "Content-Type:application/json")
    @POST("MobileMainPage/GetMainSliders")
    suspend fun postBannerAds(@Body googleId : GoogleId) :Response<List<BannerList>>

    @Headers("Lang:en" , "Content-Type:application/json")
    @GET("Categories/index")
    suspend fun getCategories() : Response<List<CategoriesList>>


    @Headers("Lang:en" , "Content-Type:application/json")
    @POST("MobileMainPage/GetHomePage")
    suspend fun postBestSeller(@Body googleId : GoogleId) :Response<BestSallerList>



}