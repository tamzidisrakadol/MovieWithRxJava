package com.example.moviewithrxjava.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviewithrxjava.adapter.PopularMoviePagedListAdapter
import com.example.moviewithrxjava.databinding.ActivityMainBinding
import com.example.moviewithrxjava.network.MovieDBClient
import com.example.moviewithrxjava.repository.MoviePagedListRepository
import com.example.moviewithrxjava.viewModel.PopularMovieViewModel
import com.example.moviewithrxjava.viewModel.PopularViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PopularMovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PopularMoviePagedListAdapter(this)
        val apiService = MovieDBClient.getClient()
        val repository = MoviePagedListRepository(apiService)
        viewModel = ViewModelProvider(this,PopularViewModelFactory(repository))[PopularMovieViewModel::class.java]

        val gridLayoutManager = GridLayoutManager(this,3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                val viewType = adapter.getItemViewType(position)
                return if (viewType==adapter.getItemViewType(position)) 1
                else 3
            }
        }

        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        viewModel.moviePagedList.observe(this, Observer {
            Log.d("size","${it.size}")
        })
        viewModel.networkState.observe(this,Observer{
            if (!viewModel.isEmptyList()) {
                adapter.setNetworkState(it)
            }
        })


    }
}