package com.example.mohamedelsayedsalemandroidtask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mohamedelsayedsalemandroidtask.R
import com.example.mohamedelsayedsalemandroidtask.data.CategoriesData.CategoriesList
import com.example.mohamedelsayedsalemandroidtask.databinding.CategoryRowBinding

class CategoriesAdapter() :RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {



    private val diffUtil = object :DiffUtil.ItemCallback<CategoriesList>(){
        override fun areItemsTheSame(oldItem: CategoriesList, newItem: CategoriesList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoriesList, newItem: CategoriesList): Boolean {
            return  oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this , diffUtil)

    class ViewHolder (val binding : CategoryRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(CategoryRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        Glide.with(holder.itemView)
            .load(data.photo)
            .error(R.drawable.orange)
            .into(holder.binding.imgCategory)

        holder.binding.restaurantName.text = data.name

    }

    override fun getItemCount() = differ.currentList.size
}