package com.example.mohamedelsayedsalemandroidtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mohamedelsayedsalemandroidtask.R
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.DataXX
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.DataXXX
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.DataXXXX
import com.example.mohamedelsayedsalemandroidtask.databinding.BestSellerRowBinding
import com.example.mohamedelsayedsalemandroidtask.databinding.MostPopularRowBinding

class MostPopularAdapter() :RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {



    private val diffUtil = object :DiffUtil.ItemCallback<DataXXX>(){
        override fun areItemsTheSame(oldItem: DataXXX, newItem: DataXXX): Boolean {
            return oldItem.itemamount == newItem.itemamount
        }

        override fun areContentsTheSame(oldItem: DataXXX, newItem: DataXXX): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this , diffUtil)

    class ViewHolder (val binding : MostPopularRowBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return  ViewHolder(MostPopularRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val data = differ.currentList[position].menu_categories_items


        Glide.with(holder.itemView)
            .load(data.photo)
            .error(R.drawable.meal)
            .into(holder.binding.imgMostPopular)



        holder.binding.apply {
            mealName.text        = data.name
            mealDescription.text = data.descriptions
        }





    }

    override fun getItemCount()  = differ.currentList.size

}