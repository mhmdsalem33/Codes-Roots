package com.example.mohamedelsayedsalemandroidtask.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mohamedelsayedsalemandroidtask.R
import com.example.mohamedelsayedsalemandroidtask.adapters.BestSellerAdapter
import com.example.mohamedelsayedsalemandroidtask.adapters.CategoriesAdapter
import com.example.mohamedelsayedsalemandroidtask.adapters.MostPopularAdapter
import com.example.mohamedelsayedsalemandroidtask.adapters.ViewPagerAdapter
import com.example.mohamedelsayedsalemandroidtask.data.BannerAds.BannerList
import com.example.mohamedelsayedsalemandroidtask.databinding.ActivityMainBinding
import com.example.mohamedelsayedsalemandroidtask.util.Resource
import com.example.mohamedelsayedsalemandroidtask.viewModels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    // linked in https://www.linkedin.com/in/mhmd-salem-a004a0213/

    private lateinit var binding : ActivityMainBinding
    private val mainMvvm         : MainActivityViewModel by viewModels()

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler   : Handler
    private lateinit var viewPagerAdapter : ViewPagerAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var bestSellerAdapter: BestSellerAdapter
    private lateinit var mostPopularAdapter : MostPopularAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoriesAdapter  = CategoriesAdapter()
        bestSellerAdapter  = BestSellerAdapter()
        mostPopularAdapter = MostPopularAdapter()


        mainMvvm.getBannerAdsData()
        loadDataViewPager()
        setUpTransFormer()
        handleViewPager()

        observeCategoriesData()
        setupCategoriesRecView()



        mainMvvm.getBestSeller()
        setupRecViewBestSeller()
        getDataBestSeller()

        setupMostPopularRecView()
        observerMostPopularData()

       



    }




    // START VIEW PAGER
    private fun handleViewPager() {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 3000)
            }
        })
    }
    private fun loadDataViewPager() {

        viewPager2 = binding.root.findViewById(R.id.viewpagger)
        handler    = Handler(Looper.myLooper()!!)
        viewPagerAdapter = ViewPagerAdapter(viewPager2)

        lifecycleScope.launchWhenStarted {
            mainMvvm.getBannerAdsData.collect{ Banner ->
                when(Banner)
                {
                    is Resource.Loading ->
                    {
                        Log.d("testApp" , "Data Banner Ads is Loading")
                    }
                    is Resource.Success ->
                    {
                        viewPagerAdapter.setViewPager(viewPagerList =  Banner.data as ArrayList<BannerList>)
                        Log.d("testApp" , "Data Banner Ads is Success ")
                    }
                    is Resource.Error ->
                    {
                        Log.d("testApp" , "Data Banner Ads is Failed")
                    }

                    else -> Unit
                }
            }
        }

        viewPager2.adapter = viewPagerAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren  = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
    private fun setUpTransFormer() {
        val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            transformer.addTransformer{page , position ->

            val r = 1  - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }
    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)

    }
    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable , 2000)
    }
    // END VIEW PAGER


    private fun setupCategoriesRecView() {
        binding.categoriesRec.apply {
            layoutManager = GridLayoutManager(context , 3 , RecyclerView.VERTICAL , false)
            adapter       = categoriesAdapter
        }
    }

    private fun observeCategoriesData() {
        mainMvvm.getCategoriesData()
        mainMvvm.getCategoriesLiveData.observe(this){
            when(it)
            {
                is  Resource.Loading  ->  Log.d("testApp" , "Data Get Categories is Loading")
                is  Resource.Success  ->  categoriesAdapter.differ.submitList(it.data)
                is  Resource.Error    ->  Log.d("testApp" , it.message.toString())
                else -> Unit
            }
        }
    }


    private fun getDataBestSeller() {
        lifecycleScope.launchWhenStarted {
            mainMvvm.getBestSellerData.collect{data ->
                when(data)
                {
                    is Resource.Loading -> Log.d("testApp" , "Best Seller Loading")
                    is Resource.Success -> bestSellerAdapter.differ.submitList(data.data)
                    is Resource.Error   -> Log.d("testApp" , data.message.toString())
                    else -> Unit
                }
            }
        }
    }

    private fun setupRecViewBestSeller() {
        binding.bestSellerRec.apply {
            layoutManager = LinearLayoutManager(context , RecyclerView.HORIZONTAL , false)
            adapter       = bestSellerAdapter
        }
    }



    private fun observerMostPopularData() {
        mainMvvm.getMostPopularData.observe(this){ data ->
            mostPopularAdapter.differ.submitList(data)
        }
    }

    private fun setupMostPopularRecView() {
        binding.mostPopularRec.apply {
            layoutManager = LinearLayoutManager(context , RecyclerView.HORIZONTAL ,false)
            adapter       = mostPopularAdapter
        }
    }


}