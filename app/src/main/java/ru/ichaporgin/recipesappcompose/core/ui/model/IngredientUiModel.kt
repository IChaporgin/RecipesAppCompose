package ru.ichaporgin.recipesappcompose.core.ui.model

import androidx.compose.runtime.Immutable
import ru.ichaporgin.recipesappcompose.data.model.IngredientDto

@Immutable
data class IngredientUiModel(
    val name: String,
    val amount: String
)

fun IngredientDto.toUiModel() = IngredientUiModel(
    name = description,
    amount = "$quantity $unitOfMeasure"

)
