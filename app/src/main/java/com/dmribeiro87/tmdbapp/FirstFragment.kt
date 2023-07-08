package com.dmribeiro87.tmdbapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.dmribeiro87.data.remote.RetrofitClient
import com.dmribeiro87.data.repository.MovieRepository
import com.dmribeiro87.tmdbapp.databinding.FragmentFirstBinding
import com.dmribeiro87.view.HomeAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val adapter: HomeAdapter by lazy {  HomeAdapter() }

    private var _binding: FragmentFirstBinding? = null
    private lateinit var repository: MovieRepository
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        repository = MovieRepository()
        addObserver()

    }

    private fun addObserver() {

        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovies.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
            val data = repository.getNowPlaying(1)
            Log.d("***Data", data.body().toString())

            adapter.setData( data.body()!!.movies)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}