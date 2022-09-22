package com.example.mohamedelsayedsalemandroidtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mohamedelsayedsalemandroidtask.R
import com.example.mohamedelsayedsalemandroidtask.data.BestSellerData.DataXX
import com.example.mohamedelsayedsalemandroidtask.databinding.BestSellerRowBinding

class BestSellerAdapter() :RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {



    private val diffUtil = object :DiffUtil.ItemCallback<DataXX>(){
        override fun areItemsTheSame(oldItem: DataXX, newItem: DataXX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataXX, newItem: DataXX): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this , diffUtil)

    class ViewHolder (val binding : BestSellerRowBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return  ViewHolder(BestSellerRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val data = differ.currentList[position].restaurants

      Glide.with(holder.itemView)
          .load(data.cover)
          .error(R.drawable.meal)
          .into(holder.binding.imgBestSeller)

        Glide.with(holder.itemView)
            .load(data.logo)
            .error(R.drawable.orange)
            .into(holder.binding.imageLogo)

        holder.binding.apply {
            resName.text = data.name
            if (data.IsOpen == "false")
            {
                isOpen.text = "مغلق"
            }
            else
            {
                isOpen.text = "مفتوح"
            }
        }




    }

    override fun getItemCount()  = differ.currentList.size

}