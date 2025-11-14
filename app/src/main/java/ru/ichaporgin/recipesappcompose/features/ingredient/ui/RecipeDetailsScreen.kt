package ru.ichaporgin.recipesappcompose.features.ingredient.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.ScreenHeader
import ru.ichaporgin.recipesappcompose.core.ui.model.IngredientUiModel
import ru.ichaporgin.recipesappcompose.core.ui.model.RecipeUiModel
import ru.ichaporgin.recipesappcompose.core.ui.theme.AccentBlue
import ru.ichaporgin.recipesappcompose.core.ui.theme.SliderTrackColor
import ru.ichaporgin.recipesappcompose.core.ui.theme.TextSecondaryColor
import ru.ichaporgin.recipesappcompose.core.ui.theme.recipesAppTypography
import ru.ichaporgin.recipesappcompose.core.ui.utils.ShareUtils
import kotlin.math.roundToInt


@Composable
fun RecipeDetailScreen(
    recipe: RecipeUiModel
) {

    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val defaultImageRes = R.drawable.img_placeholder
    var recipeTitle by remember { mutableStateOf("") }
    var recipeImage by remember { mutableStateOf<String?>(null) }
    var ingredients by remember { mutableStateOf<List<IngredientUiModel>>(emptyList()) }
    var method by remember { mutableStateOf<List<String>>(emptyList()) }
    var currentPortions by remember { mutableStateOf(recipe.serving) }

    recipeTitle = recipe.title
    recipeImage = recipe.imageUrl
    ingredients = recipe.ingredients
    method = recipe.method

    val scaledIngredients = remember(currentPortions) {
        val multiplier = currentPortions.toDouble() / recipe.serving
        ingredients.map { ingredient ->
            val amountDouble = ingredient.amount.toDoubleOrNull()
            ingredient.copy(
                amount = if (amountDouble != null)
                    (amountDouble * multiplier).let {
                        if (it % 1 == 0.0) it.toInt().toString()
                        else
                            "%.1f".format(it)
                    } else {
                    ingredient.amount
                }
            )
        }
    }

    if (isLoading) {
        androidx.compose.material3.CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp)
                .wrapContentWidth()
        )
    } else {
        LazyColumn {
            item {
                ScreenHeader(recipeTitle.uppercase(), recipeImage, defaultImageRes)
                ShareIconButton(onClick = {
                    ShareUtils.shareRecipe(
                        context = context,
                        recipeId = recipe.id.toString(),
                        recipeTitle = recipe.title
                    )
                })
            }
            item { PortionsSlider(currentPortions, { newValue -> currentPortions = newValue }) }
            item { IngredientsList(scaledIngredients) }
            item { InstructionsList(method) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortionsSlider(
    currentPortions: Int,
    onPortionsChange: (Int) -> Unit
) {
    Text(
        text = stringResource(R.string.ingredient_item_title).uppercase(),
        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
        style = recipesAppTypography.displayLarge,
        color = MaterialTheme.colorScheme.tertiary
    )
    Text(
        text = pluralStringResource(
            id = R.plurals.portions_count,
            count = currentPortions,
            currentPortions
        ),
        style = recipesAppTypography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(start = 16.dp, top = 6.dp)
    )
    val interactionSource = remember { MutableInteractionSource() }
    Slider(
        value = currentPortions.toFloat(),
        onValueChange = { onPortionsChange(it.roundToInt()) },
        valueRange = 1f..12f,
        steps = 10,
        colors = SliderDefaults.colors(
            thumbColor = AccentBlue,
            activeTrackColor = SliderTrackColor,
        ),
        modifier = Modifier.padding(horizontal = 16.dp),
        thumb = {
            SliderDefaults.Thumb(
                interactionSource = interactionSource,
                thumbSize = DpSize(8.dp, 30.dp)
            )
        }
    )
}

@Composable
fun IngredientsList(ingredients: List<IngredientUiModel>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        ingredients.forEachIndexed { index, ingredient ->
            IngredientItem(ingredient)

            if (index < ingredients.lastIndex) {
                HorizontalDivider(
                    color = TextSecondaryColor.copy(alpha = 0.2f),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun InstructionsList(method: List<String>) {
    Text(
        text = stringResource(R.string.method_item_title).uppercase(),
        modifier = Modifier.padding(all = 16.dp),
        style = recipesAppTypography.displayLarge,
        color = MaterialTheme.colorScheme.tertiary
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        method.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. ${step}",
                modifier = Modifier.padding(vertical = 4.dp),
                style = recipesAppTypography.titleSmall,
                color = TextSecondaryColor
            )
            if (index < method.lastIndex) {
                HorizontalDivider(
                    color = TextSecondaryColor.copy(alpha = 0.2f),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

    }
}

@Composable
fun ShareIconButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = stringResource(R.string.share_recipe),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
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
            IngredientUiModel(name = "Лук", amount = "2", "шт."),
            IngredientUiModel(name = "Спагетти", amount = "200", "г"),
            IngredientUiModel(name = "Бекон", amount = "100", "г"),
            IngredientUiModel(name = "Сливки", amount = "100", "мл")
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