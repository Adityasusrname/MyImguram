package com.afterclass.myimguram.ui.stories

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afterclass.libimgur.models.Image
import com.afterclass.myimguram.databinding.PageItemStoryBinding
import com.bumptech.glide.Glide

class StoryViewPageAdapter: ListAdapter<Image, StoryViewPageAdapter.StoryPageViewHolder>( object: DiffUtil.ItemCallback<Image>(){

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return  oldItem.toString() == newItem.toString()
    }

}) {
    private lateinit var  _context:Context

    class StoryPageViewHolder(val binding:PageItemStoryBinding):RecyclerView.ViewHolder(binding.root)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryPageViewHolder {
        val inflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = PageItemStoryBinding.inflate(inflater,parent,false)
        _context=parent.context
        return StoryPageViewHolder(binding)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: StoryPageViewHolder, position: Int) {
        val image = getItem(position)
        if(image.isAlbum==true && image.imagesCount!! >0){
            Glide.with(_context).load(image.images?.get(0)?.link).into(holder.binding.ivStory)
        }
        else{
            Glide.with(_context).load(image.link).into(holder.binding.ivStory)
        }
    }
}