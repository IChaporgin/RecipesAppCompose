package ru.ichaporgin.recipesappcompose.core.ui.model

import androidx.compose.runtime.Immutable
import ru.ichaporgin.recipesappcompose.data.model.RecipeDto

@Immutable
data class RecipeUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val categoryTitle: String
)

fun RecipeDto.toUiModel(categoryTitle: String) = RecipeUiModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    categoryTitle = categoryTitle
)