package ru.ichaporgin.recipesappcompose.core.ui.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import ru.ichaporgin.recipesappcompose.data.model.IngredientDto

@Immutable
@Parcelize
data class IngredientUiModel(
    val name: String,
    val amount: String
) : Parcelable

fun IngredientDto.toUiModel() = IngredientUiModel(
    name = description,
    amount = "$quantity $unitOfMeasure"
)
