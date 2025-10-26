package ru.ichaporgin.recipesappcompose.features.recipes.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.ScreenHeader
import ru.ichaporgin.recipesappcompose.core.ui.model.RecipeUiModel
import ru.ichaporgin.recipesappcompose.core.ui.model.toUiModel
import ru.ichaporgin.recipesappcompose.data.repository.RecipesRepositoryStub
import ru.ichaporgin.recipesappcompose.core.ui.model.CategoryUiModel

@Composable
fun RecipesScreen(
    categoryId: Int,
) {
    val repository = RecipesRepositoryStub
    val context = LocalContext.current
    var recipes by remember { mutableStateOf<List<RecipeUiModel>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    val defaultImageRes = R.drawable.img_placeholder
    var categoryTitle by remember { mutableStateOf("") }
    var categoryImage by remember { mutableStateOf<String?>(null) }
    
    LaunchedEffect(categoryId) {
        isLoading = true
        try {
            Log.d("RecipesScreen", "Loading recipes for categoryId = $categoryId")
            kotlinx.coroutines.delay(1000)

            val categories = repository.getCategories(context)
                .map { it.toUiModel() }
            val category = categories.find { it.id == categoryId }

            if (category != null) {
                categoryTitle = category.title
                categoryImage = category.imageUrl
            }

            recipes = repository.getRecipesByCategoryId(context, categoryId)
                .map { it.toUiModel() }
            
            Log.d("RecipesScreen", "Found category: title=$categoryTitle, image=$categoryImage")
            Log.d("RecipesScreen", "Found ${recipes.size} recipes")
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
            ScreenHeader(categoryTitle.uppercase(), categoryImage, defaultImageRes)
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
                    items(recipes, key = { it.id }) { recipe ->
                        RecipeItem(
                            recipe = recipe,
                            onRecipeClick = {},
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RecipesScreenPreview() {
    RecipesScreen(
        categoryId = 0,
    )
}