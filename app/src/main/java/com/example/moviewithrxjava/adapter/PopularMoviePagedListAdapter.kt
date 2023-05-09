package com.example.moviewithrxjava.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedList
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviewithrxjava.R
import com.example.moviewithrxjava.model.MovieModel
import com.example.moviewithrxjava.repository.NetworkState
import com.example.moviewithrxjava.repository.Status
import com.example.moviewithrxjava.utility.Constraints
import com.example.moviewithrxjava.views.MovieDetailActivity

class PopularMoviePagedListAdapter(private val context: Context) : PagingDataAdapter<MovieModel, ViewHolder>(MovieDiffCallback()) {

    private val DATA_VIEW_TYPE = 1
    private val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            (holder as MovieItemViewHolder).bind(getItem(position),context)
        }
        else {
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        return if (viewType == DATA_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
            MovieItemViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.network_state, parent, false)
            NetworkStateItemViewHolder(view)
        }

    }
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.Loaded
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            DATA_VIEW_TYPE
        }
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
        private val movieName:TextView = itemView.findViewById(R.id.movieNameTV)
        private val movieImg:ImageView =itemView.findViewById(R.id.movieImg)

        fun bind(movieModel: MovieModel?, context: Context){
            movieName.text = movieModel?.title
            val posterUrl = Constraints.posterUrl+movieModel?.posterPath
            Glide.with(itemView.context).load(posterUrl).into(movieImg)

            itemView.setOnClickListener {
                val intent = Intent(context,MovieDetailActivity::class.java)
                intent.putExtra("id",movieModel?.id)
                context.startActivity(intent)
            }

        }
    }

    class NetworkStateItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val progressBar:ProgressBar = itemView.findViewById(R.id.progressBar)
        private val msg:TextView = itemView.findViewById(R.id.errorMsgTV)
        fun bind(networkState: NetworkState?){
            if (networkState != null && networkState.status ==Status.Running){
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.GONE
            }

            if (networkState!=null && networkState.status==Status.Failed){
                progressBar.visibility = View.VISIBLE
                msg.text = networkState.msg
            }else{
                msg.visibility = View.GONE
            }
        }
    }
    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }



}