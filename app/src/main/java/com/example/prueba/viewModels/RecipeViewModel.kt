package com.example.prueba.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba.models.RecipesResponse
import com.example.prueba.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class RecipeViewModel:ViewModel() {
    private val _recipes = MutableLiveData<Result<RecipesResponse>>()
    val recipes: MutableLiveData<Result<RecipesResponse>> get() = _recipes

    init {
        fetchRecipes()
    }
    private fun fetchRecipes() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getRecipes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _recipes.value = Result.success(it)
                        Log.d("RecipeViewModel", "Recipes fetched successfully from API $it")
                    } ?: run {
                        _recipes.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    _recipes.value = Result.failure(Exception("Error: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                _recipes.value = Result.failure(e)
            }
        }
    }
}