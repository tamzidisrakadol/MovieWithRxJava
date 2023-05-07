package com.example.moviewithrxjava.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviewithrxjava.model.MovieModel

class PopularMoviePagedListAdapter : PagedListAdapter<MovieModel,RecyclerView.ViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieModel>(){

        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }

    }
    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(movieModel: MovieModel?, context: Context){

        }
    }
}