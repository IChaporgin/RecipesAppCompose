package ru.ichaporgin.recipesappcompose.features.ingredient.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.Constants
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.ScreenHeader
import ru.ichaporgin.recipesappcompose.core.ui.model.IngredientUiModel
import ru.ichaporgin.recipesappcompose.core.ui.model.RecipeUiModel
import ru.ichaporgin.recipesappcompose.core.ui.theme.recipesAppTypography


@Composable
fun RecipeDetailScreen(
    recipe: RecipeUiModel
) {
    var isLoading by remember { mutableStateOf(false) }
    val defaultImageRes = R.drawable.img_placeholder
    var recipeTitle by remember { mutableStateOf("") }
    var recipeImage by remember { mutableStateOf<String?>(null) }
    var ingredients by remember { mutableStateOf<List<IngredientUiModel>>(emptyList()) }
    var method by remember { mutableStateOf<List<String>>(emptyList()) }

    recipeTitle = recipe.title
    recipeImage = recipe.imageUrl
    ingredients = recipe.ingredients
    method = recipe.method

    LaunchedEffect(recipe) {
        isLoading = true
        try {
            Log.d("RecipeDetailScreen", "Loading ingredient for recipe = ${recipe.title}")
            kotlinx.coroutines.delay(1000)
            Log.d("IngredientScreen", "Found recipe: title=$recipeTitle, image=$recipeImage")
        } finally {
            isLoading = false
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    )
    {
        ScreenHeader(recipeTitle.uppercase(), recipeImage, defaultImageRes)

        Text(
            text = Constants.INGREDIENT_TITLE.uppercase(),
            style = recipesAppTypography.displayLarge,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(all = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(ingredients) { ingredient ->
                    Text(ingredient.name.uppercase(), style = recipesAppTypography.bodyMedium)
                }
            }
        }

        Text(
            text = Constants.METHOD_TITLE.uppercase(),
            style = recipesAppTypography.displayLarge,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(method) { step ->
                    Text(step, style = recipesAppTypography.bodyMedium)
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
private fun RecipeDetailsScreenPreview() {
    val mockRecipe = RecipeUiModel(
        id = 1,
        title = "Pasta Carbonara",
        imageUrl = "https://example.com/pasta.jpg",
        ingredients = listOf(
            IngredientUiModel(name = "Лук", amount = "2 шт."),
            IngredientUiModel(name = "Спагетти", amount = "200 г"),
            IngredientUiModel(name = "Бекон", amount = "100 г"),
            IngredientUiModel(name = "Сливки", amount = "100 мл")
        ),
        method = listOf(
            "Поставить воду для пасты.",
            "Обжарить бекон до золотистой корочки.",
            "Смешать со сливками и специями.",
            "Добавить пасту и перемешать."
        ),
        isFavorite = false
    )

    RecipeDetailScreen(recipe = mockRecipe)
}