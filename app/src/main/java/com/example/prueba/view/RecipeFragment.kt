package com.example.prueba.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba.databinding.FragmentRecipeBinding
import com.example.prueba.viewModels.RecipeViewModel


class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModels()
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

        val recyclerView:RecyclerView = binding.recipeRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)
        recipeViewModel.recipes.observe(viewLifecycleOwner, Observer{ result ->
            result.fold(
                onSuccess = {
                   Log.d("RecipeFragment", "Recipes: ${it.data}")
                    recyclerView.adapter = RecipeAdapter(it.data)
                },
                onFailure = {
                    // Handle error
                    Log.e("RecipeFragment", "Error: ${it.message}")
                }
            )
        })
    }

}