package com.dmribeiro87.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dmribeiro87.data.model.Movie
import com.dmribeiro87.data.utils.Utils.Companion.toGenre
import com.dmribeiro87.tmdbapp.R
import com.dmribeiro87.tmdbapp.databinding.ItemMovieBinding

import com.example.desafiostant.view.fragments.home.adapters.MovieDiffUtil

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    private var dataList = emptyList<Movie>()
    class ListViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = dataList[position].title
            tvYear.text = dataList[position].releaseDate.subSequence(0,4)
            if (dataList[position].genreIds?.isNullOrEmpty() == true){
                tvGenre.text = "No Gender"
            }else{
                tvGenre.text = toGenre(dataList[position].genreIds[0])
            }

            Log.d("***gender_ids ->", "${dataList[position].title}")
            Glide.with(ivCover)
                .load("https://image.tmdb.org/t/p/w500/" + dataList[position].posterPath)
                .placeholder(R.drawable.ic_movies)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivCover)

//            holder.itemView.setOnClickListener {
//                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(dataList[position])
//                holder.itemView.findNavController().navigate(action)
//                Log.d("***HomeAdapter", "${tvTitle.text}")
//            }
        }
    }

    override fun getItemCount(): Int{
        return dataList.size
    }

    fun setData(movieList: List<Movie>){
        this.dataList = movieList
        notifyDataSetChanged()
    }


}
