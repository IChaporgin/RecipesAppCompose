package ru.ichaporgin.recipesappcompose.features.ingredient.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.ScreenHeader
import ru.ichaporgin.recipesappcompose.core.ui.model.IngredientUiModel
import ru.ichaporgin.recipesappcompose.core.ui.model.RecipeUiModel
import ru.ichaporgin.recipesappcompose.data.repository.RecipesRepositoryStub


@Composable
fun IngredientScreen(
    navController: NavController,
) {
    val repository = RecipesRepositoryStub
    val recipe =
        navController.previousBackStackEntry?.savedStateHandle?.get<RecipeUiModel>("recipe")
    var isLoading by remember { mutableStateOf(false) }
    val defaultImageRes = R.drawable.img_placeholder
    var recipeTitle by remember { mutableStateOf("") }
    var recipeImage by remember { mutableStateOf<String?>(null) }
    var ingredients by remember { mutableStateOf<List<IngredientUiModel>>(emptyList()) }


    LaunchedEffect(recipe) {
        isLoading = true
        try {
            Log.d("IngredientScreen", "Loading ingredient for recipe = ${recipe?.title}")
            kotlinx.coroutines.delay(1000)

            if (recipe != null) {
                recipeTitle = recipe.title
                recipeImage = recipe.imageUrl
            }

            Log.d("IngredientScreen", "Found recipe: title=$recipeTitle, image=$recipeImage")
        } finally {
            isLoading = false
        }
    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            ScreenHeader(recipeTitle.uppercase(), recipeImage, defaultImageRes)
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(ingredients.size) { index ->
                        val ingredient = ingredients[index]
                        Text(text = ingredient.name)
                        Text(text = ingredient.amount)
                    }
                }
            }
        }
    }
}

//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun IngredientScreenPreview() {
//    val mockRecipe = RecipeUiModel(
//        id = 1,
//        title = "Pasta Carbonara",
//        imageUrl = "https://example.com/pasta.jpg",
//        ingredients = emptyList(),
//        method = listOf("Boil pasta", "Mix with sauce"),
//        isFavorite = false
//    )
//
//    val mockIngredient = IngredientUiModel(
//        name = "Parmesan cheese",
//        amount = "50 g"
//    )
//
//    IngredientScreen(
//        recipe = mockRecipe,
//        ingredient = mockIngredient
//    )
//}