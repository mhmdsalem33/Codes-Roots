package com.example.mohamedelsayedsalemandroidtask.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohamedelsayedsalemandroidtask.Repositores.MainActivityRepository
import com.example.mohamedelsayedsalemandroidtask.data.BannerAds.BannerList
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.DataXX
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.DataXXX
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.DataXXXX
import com.example.mohamedelsayedsalemandroidtask.data.CategoriesData.CategoriesList
import com.example.mohamedelsayedsalemandroidtask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(private val mainRepo :MainActivityRepository) :ViewModel() {


    private var _getBannerAdsData = MutableStateFlow<Resource<List<BannerList>>>(Resource.Undefined())
    var getBannerAdsData :StateFlow<Resource<List<BannerList>>> = _getBannerAdsData

    fun getBannerAdsData () = viewModelScope.launch {
        try {
            val response = mainRepo.postBannerAds()
            if (response.isSuccessful)
            {
              _getBannerAdsData.emit(Resource.Loading())
                response.body()?.let {
                    _getBannerAdsData.emit(Resource.Success(it))
                }
            }
            else
            {
                _getBannerAdsData.emit(Resource.Error(message =  response.message().toString()))
            }
        }catch (t:Throwable)
        {
            Log.d("testApp" , t.message.toString())
        }

    }


    private var _getCategoriesLiveData = MutableLiveData<Resource<List<CategoriesList>>>()
    var getCategoriesLiveData :LiveData<Resource<List<CategoriesList>>> = _getCategoriesLiveData


    fun getCategoriesData() = viewModelScope.launch {
        try {
               val response = mainRepo.getCategories()
                if (response.isSuccessful)
                {
                   response.body()?.let {
                       _getCategoriesLiveData.postValue(Resource.Success(it))
                   }
                }
        }catch (t:Throwable)
        {
            Log.d("testApp" ,  t.message.toString())
        }

    }



    private var _getBestSellerData = MutableStateFlow<Resource<List<DataXX>>>(Resource.Undefined())
    var getBestSellerData : Flow<Resource<List<DataXX>>> = _getBestSellerData


     private var _getMostPopularData = MutableLiveData<List<DataXXX>>()
     var getMostPopularData : LiveData<List<DataXXX>> = _getMostPopularData


    fun getBestSeller() = viewModelScope.launch {
        try {
            val response = mainRepo.getBestSeller()
            if (response.isSuccessful)
            {
                   _getBestSellerData.emit(Resource.Loading())
                // اعتقد المقصود نجيب اللى علية خصومات!
                val discountData = response.body()?.GetPercentageForVendors?.data
                    discountData?.let {
                        _getBestSellerData.emit(Resource.Success(it))
                    }
                // اشهر الاطباق
                val popularData = response.body()?.MostSellItems?.data
                    popularData?.let {
                        _getMostPopularData.postValue(it)
                    }
            }
            else
            {
                _getBestSellerData.emit(Resource.Error(message = response.message().toString()))
            }
        }catch (t:Throwable)
        {
            Log.d("testApp" , t.message.toString())
        }
    }





}