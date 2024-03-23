package com.dicoding.tugasgithubapi.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.tugasgithubapi.data.response.ItemsItem
import com.dicoding.tugasgithubapi.databinding.ItemUserBinding

class DetailUserAdapter: androidx.recyclerview.widget.ListAdapter<ItemsItem, DetailUserAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyviewHolder(private  val binding: ItemUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemName: ItemsItem) {
            binding.tvItemName.text = itemName.login
            Glide.with(binding.root)
                .load(itemName.avatarUrl)
                .into(binding.imgUserPhoto)
            binding.root.setOnClickListener {
                val intentDetail = Intent(binding.root.context, DetailActivity::class.java)
                intentDetail.putExtra("ID", itemName.id)
                intentDetail.putExtra("USERNAME", itemName.login)
                intentDetail.putExtra("AVATAR", itemName.avatarUrl)
                binding.root.context.startActivity(intentDetail)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}