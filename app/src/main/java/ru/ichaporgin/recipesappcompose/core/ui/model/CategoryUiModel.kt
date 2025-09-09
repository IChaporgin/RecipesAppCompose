package ru.ichaporgin.recipesappcompose.core.ui.model

import androidx.compose.runtime.Immutable
import ru.ichaporgin.recipesappcompose.Constants
import ru.ichaporgin.recipesappcompose.data.model.CategoryDto

@Immutable
data class CategoryUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
)

fun CategoryDto.toUiModel() = CategoryUiModel(
    id = id,
    title = title,
    imageUrl = Constants.ASSETS_URI_PREFIX + imageUrl
)