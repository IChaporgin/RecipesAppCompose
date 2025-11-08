package ru.ichaporgin.recipesappcompose.features.ingredient.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.core.ui.model.IngredientUiModel
import ru.ichaporgin.recipesappcompose.core.ui.theme.TextSecondaryColor
import ru.ichaporgin.recipesappcompose.core.ui.theme.recipesAppTypography

@Composable
fun IngredientItem(ingredient: IngredientUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
    ) {
        Text(
            text = ingredient.name.uppercase(),
            style = recipesAppTypography.titleSmall,
            color = TextSecondaryColor,
            modifier = Modifier.weight(1f)
            )
        Text(
            ingredient.amount.toString().uppercase(),
            style = recipesAppTypography.titleSmall,
            color = TextSecondaryColor
        )
        Spacer(Modifier.width(5.dp))
        Text(
            ingredient.unitOfMeasure.uppercase(),
            style = recipesAppTypography.titleSmall,
            color = TextSecondaryColor
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false
)
@Composable
private fun IngredientItemPreview() {
    IngredientItem(
        ingredient = IngredientUiModel(
            name = "water",
            amount = "200",
            unitOfMeasure = "ml"
        )
    )
}

