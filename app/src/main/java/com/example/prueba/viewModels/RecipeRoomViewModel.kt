package com.example.prueba.viewModels
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba.entity.RecipeDao
import com.example.prueba.entity.RecipeEntity
import com.example.prueba.models.RecipesResponse
import com.example.prueba.retrofit.RetrofitClient
import kotlinx.coroutines.launch

val dummyRecipes = listOf(
    RecipeEntity(
        id = 1,
        title = "California Roll Sushi Bowls",
        description = "A delicious sushi bowl...",
        tags = "Asian",
        photoUrl = "url_to_photo1",
        ingredients = "Rice, Crab, Avocado, etc.",
        directions = "1. Prepare rice...",
        totalTime = 30,
        cuisine = "Asian",
        rating = 4.5
    ),
    RecipeEntity(
        id = 2,
        title = "Chicken Caesar Wraps",
        description = "Tasty and easy to make...",
        tags = "American",
        photoUrl = "url_to_photo2",
        ingredients = "Chicken, Lettuce, Caesar dressing, etc.",
        directions = "1. Grill chicken...",
        totalTime = 20,
        cuisine = "American",
        rating = 4.0
    )
    // Agrega más recetas si es necesario
)

class RecipeRoomViewModel(private val recipeDao: RecipeDao): ViewModel() {

    private val _recipesFromDb = MutableLiveData<List<RecipeEntity>>()
    val recipesFromDb: LiveData<List<RecipeEntity>> get() = _recipesFromDb

    init {
        insertDummyData()
        fetchAllRecipes()
    }

    private fun insertDummyData() {
        viewModelScope.launch {
            recipeDao.insertAll(dummyRecipes)
        }
    }

    private fun fetchAllRecipes() {
        viewModelScope.launch {
            _recipesFromDb.value = recipeDao.getAllRecipes()
        }
    }
}
