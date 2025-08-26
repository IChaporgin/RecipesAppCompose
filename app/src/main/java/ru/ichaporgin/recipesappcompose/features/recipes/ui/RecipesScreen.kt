package ru.ichaporgin.recipesappcompose.features.recipes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.ScreenHeader
import ru.ichaporgin.recipesappcompose.core.ui.theme.recipesAppTypography

val title = "Рецепты"
val image = R.drawable.burger

@Composable
fun RecipesScreen(){
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            ScreenHeader(title, image)
        }
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text("Скоро здесь будет список рецептов",
                style = recipesAppTypography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RecipesScreenPreview(){
    RecipesScreen()
}