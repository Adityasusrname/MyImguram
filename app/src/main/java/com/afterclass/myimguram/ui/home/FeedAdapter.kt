package com.afterclass.myimguram.ui.home

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afterclass.libimgur.models.Image
import com.afterclass.myimguram.R
import com.afterclass.myimguram.databinding.PostContainerBinding
import com.bumptech.glide.Glide

class FeedAdapter: ListAdapter<Image, FeedAdapter.FeedViewHolder>(object:
    DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.toString() == newItem.toString()
    }

}) {

    private lateinit var  _context:Context

    class FeedViewHolder(val binding:PostContainerBinding) :RecyclerView.ViewHolder(binding.root)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
       val inflater:LayoutInflater=parent.context.getSystemService(LayoutInflater::class.java)
        val binding = PostContainerBinding.inflate(inflater,parent,false)
        _context=parent.context
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val image = getItem(position)
        Glide.with(_context).load("https://imgur.com/${image.cover}.jpg").placeholder(R.drawable.placeholder).into(holder.binding.ivImage)
        holder.binding.tvCaption.text=image.title.toString()
    }
}