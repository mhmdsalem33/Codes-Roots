package com.example.mohamedelsayedsalemandroidtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mohamedelsayedsalemandroidtask.R
import com.example.mohamedelsayedsalemandroidtask.data.BannerAds.BannerList
import com.example.mohamedelsayedsalemandroidtask.databinding.ViewPagerRowBinding


class ViewPagerAdapter( private val viewPager2: ViewPager2):RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var viewPagerList      = ArrayList<BannerList>()
    fun setViewPager(viewPagerList : ArrayList<BannerList>)
    {
        this.viewPagerList = viewPagerList
        notifyDataSetChanged()
    }


    class ViewHolder(val binding : ViewPagerRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ViewPagerRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = viewPagerList[position].AdsSpacesprice

        for (i in data)
        {
            Glide.with(holder.itemView)
                .load(i.sliders.photo)
                .error(R.drawable.error)
                .into(holder.binding.imageView)
            holder.binding.id.text = i.sliders.id.toString()
        }

        if (position == viewPagerList.size-1)
        {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount() = viewPagerList.size

    private val runnable = Runnable {
        viewPagerList.addAll(viewPagerList)
        notifyDataSetChanged()
    }
}