package com.afterclass.myimguram.ui.stories

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afterclass.libimgur.models.Tag
import com.afterclass.myimguram.databinding.StoryHeadBinding
import com.bumptech.glide.Glide

class StoryHeadRecyclerAdapter: ListAdapter<Tag, StoryHeadRecyclerAdapter.StoryHeadViewHolder>(
    object: DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.toString() == newItem.toString()
        }


    }) {

    private lateinit var  _context:Context

    class StoryHeadViewHolder(val binding:StoryHeadBinding):RecyclerView.ViewHolder(binding.root)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHeadViewHolder {
       val inflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = StoryHeadBinding.inflate(inflater,parent,false)
        _context=parent.context
        return StoryHeadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryHeadViewHolder, position: Int) {
        val tag = getItem(position)
        Glide.with(_context).load("https://imgur.com/${tag.backgroundHash}.jpg").centerCrop().into(holder.binding.ivStoryHead)
        holder.binding.tvStoryName.text = tag.name
        holder.binding.ivStoryHead.setOnClickListener {
          val intent =  Intent(_context,StoryViewActivity::class.java).apply {
                putExtra("tag",tag.name)
            }
            _context.startActivity(intent)
        }
    }
}