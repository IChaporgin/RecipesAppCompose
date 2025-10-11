package ru.ichaporgin.recipesappcompose.features.recipes.ui

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

@Composable
fun RecipesScreen(
    categoryId: Int,
    categoryTitle: String,
    categoryImage: String?,
) {
    val repository = RecipesRepositoryStub
    val context = LocalContext.current
    var recipes by remember { mutableStateOf<List<RecipeUiModel>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    val defaultImageRes = R.drawable.img_placeholder
    LaunchedEffect(categoryId) {
        isLoading = true
        try {
            kotlinx.coroutines.delay(1000)
            recipes = repository.getRecipesByCategoryId(context, categoryId)
                .map { it.toUiModel() }
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
        categoryId = 1,
        categoryTitle = "Бургеры",
        categoryImage = null
    )
}