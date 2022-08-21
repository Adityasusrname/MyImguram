package com.afterclass.myimguram.ui.stories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.afterclass.myimguram.databinding.ActivityStoryViewBinding

class StoryViewActivity : AppCompatActivity() {
    private  val viewModel:StoryViewModel by viewModels()
    private lateinit var binding:ActivityStoryViewBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStoryViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val adapter = StoryViewPageAdapter()
         val viewPager = binding.vpStories
         viewPager.adapter=adapter


        val tag = intent?.getStringExtra("tag")
        if (tag != null) {
            viewModel.get_tag(tag)
        }

        viewModel.images.observe({lifecycle}, Observer {
            adapter.submitList(it)
        })

        viewPager.registerOnPageChangeCallback(StoryPageChangeCallback)

    }
    private val nextPageRunnable = Runnable {
        binding.vpStories.currentItem++
    }
    private val StoryPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.vProgress.scaleX=0F
            binding.vProgress.animate().scaleX(1F).setDuration(5000).setStartDelay(10).start()
            handler.removeCallbacks(nextPageRunnable)
            handler.postDelayed(nextPageRunnable,5000)

        }
    }
}