package ru.ichaporgin.recipesappcompose.core.ui.model

import androidx.compose.runtime.Immutable
import ru.ichaporgin.recipesappcompose.data.model.IngredientDto
import ru.ichaporgin.recipesappcompose.data.model.RecipeDto

@Immutable
data class RecipeUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val ingredients: List<IngredientDto>,
    val method: List<String>,
    val isFavorite: Boolean = false
)

fun RecipeDto.toUiModel() = RecipeUiModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    ingredients = ingredients,
    method = method
)