package com.example.prueba.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.prueba.databinding.FragmentRecipeBinding
import com.example.prueba.models.RecipeApp
import com.example.prueba.viewModels.RecipeRoomViewModel
import com.example.prueba.viewModels.RecipeRoomViewModelFactory

//import com.example.prueba.viewModels.RecipeViewModel


class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val recipeRoomViewModel: RecipeRoomViewModel by viewModels {
        RecipeRoomViewModelFactory((requireActivity().application as
                RecipeApp).database.recipeDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val recyclerView:RecyclerView = binding.recipeRecycler
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        recipeRoomViewModel.recipesFromDb.observe(viewLifecycleOwner, Observer{ result ->
//
//        })
        recipeRoomViewModel.recipesFromDb.observe(viewLifecycleOwner, Observer
        { recipes ->
            recipes?.let {

                Log.d("RecipeFragment", "Number of recipes: ${it.size}")
            }
        })



    }

}