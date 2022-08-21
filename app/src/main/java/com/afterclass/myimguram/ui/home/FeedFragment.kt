package com.afterclass.myimguram.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afterclass.myimguram.databinding.FragmentFeedBinding


class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private  val viewModel:FeedViewModel by viewModels()
    private lateinit var _binding:FragmentFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(layoutInflater,container,false)
        val view = _binding.root
        val rvFeed=_binding.rvFeed
        val adapter = FeedAdapter()
        rvFeed.adapter=adapter
        rvFeed.layoutManager=LinearLayoutManager(requireContext())
        val feed = arguments?.getString("feed")
        when(feed){
            "hot" -> viewModel.get_gallery("hot")
            "top" -> viewModel.get_gallery("top")
            else -> null
        }
        viewModel.images.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return view
    }



}