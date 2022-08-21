package com.afterclass.myimguram

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afterclass.myimguram.databinding.ActivityMainBinding
import com.afterclass.myimguram.ui.stories.StoryHeadRecyclerAdapter
import com.afterclass.myimguram.ui.stories.StoryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel : StoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_hot,R.id.navigation_top
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val rvStories = binding.rvStories
        val adapter = StoryHeadRecyclerAdapter()
        rvStories.adapter=adapter
        rvStories.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        viewModel.get_tags()
        viewModel.tags.observe({lifecycle}, Observer {
            adapter.submitList(it)
        })





    }
}