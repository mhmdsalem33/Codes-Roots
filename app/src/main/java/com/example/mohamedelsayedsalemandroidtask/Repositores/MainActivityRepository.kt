package com.example.mohamedelsayedsalemandroidtask.Repositores

import android.util.Log
import com.example.mohamedelsayedsalemandroidtask.data.BannerAds.BannerList
import com.example.mohamedelsayedsalemandroidtask.data.BannerAds.GoogleId
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.BestSallerList
import com.example.mohamedelsayedsalemandroidtask.data.CategoriesData.CategoriesList
import com.example.mohamedelsayedsalemandroidtask.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class MainActivityRepository @Inject constructor(private val mealApi: MealApi) {


    suspend fun postBannerAds() :Response<List<BannerList>>
    {
        val response = mealApi.postBannerAds(googleId = GoogleId("ChIJ88rv8bI_WBQRkvVBLDeZQUg"))
        if (response.isSuccessful)
        {
            Log.d("testApp","Success to connect to post adds api")
            Log.d("testApp",response.code().toString())
        }
        else
        {
            Log.d("testApp" , "Failed to connect to post adds api" )
            Log.d("testApp",response.code().toString())
        }
        return response
    }


    suspend fun getCategories() : Response<List<CategoriesList>>
    {
        val response = mealApi.getCategories()
        if (response.isSuccessful)
        {
            Log.d("testApp","Success to connect to get Categories Data")
            Log.d("testApp",response.code().toString())
        }
        else
        {
            Log.d("testApp" , "Failed to connect to get Categories Data" )
            Log.d("testApp",response.code().toString())
        }
        return response
    }




    suspend fun getBestSeller() :Response<BestSallerList>
    {
        val response = mealApi.postBestSeller(googleId = GoogleId("ChIJ88rv8bI_WBQRkvVBLDeZQUg"))
        if (response.isSuccessful)
        {
            Log.d("testApp","Success to connect to get Categories Data")
            Log.d("testApp",response.code().toString())
        }
        else
        {
            Log.d("testApp" , "Failed to connect to get Categories Data" )
            Log.d("testApp",response.code().toString())
        }
        return response
    }




}