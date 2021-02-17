package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mad03_fragments_and_navigation.databinding.FragmentHomeBinding
import com.example.mad03_fragments_and_navigation.models.MovieStore


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.movies = MovieStore()   // bind a new MovieStore to the layout

        setHasOptionsMenu(true) // enable the options menu in the action bar

        subscribeUI()   // add event listeners to binding

        return binding.root
    }

    private fun subscribeUI(){
        binding.movie1Btn.setOnClickListener {
            navigateToDetail(0)
        }

        binding.movie2Btn.setOnClickListener {
            navigateToDetail(1)
        }

        binding.movie3Btn.setOnClickListener {
            navigateToDetail(2)
        }
    }

    /**
     * Navigate to the MovieDetailFragment
     * with index of clicked movie as a parameter
     */
    private fun navigateToDetail(movieIdx: Int = 0){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieIdx))
    }

    /**
     * Navigation behavior if options menu item is clicked
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    /**
     * inflate the options menu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
}