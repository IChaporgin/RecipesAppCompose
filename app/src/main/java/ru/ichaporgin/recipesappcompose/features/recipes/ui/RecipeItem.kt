package ru.ichaporgin.recipesappcompose.features.recipes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.model.RecipeUiModel
import ru.ichaporgin.recipesappcompose.core.ui.theme.recipesAppTypography

@Composable
fun RecipeItem(
    recipe: RecipeUiModel,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier
            .width(344.dp)
            .height(132.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onRecipeClick(recipe.id) },
    ){
        Column {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(R.drawable.img_placeholder),
                error = painterResource(R.drawable.img_error),
                contentScale = ContentScale.Crop
            )
            Text(text = recipe.title.uppercase(),
                style = recipesAppTypography.titleMedium,
                modifier = Modifier
                    .padding(all = 8.dp),
                color = MaterialTheme.colorScheme.tertiary)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false
)

@Composable
fun RecipeItemPreview(){
        RecipeItem(
            recipe = RecipeUiModel(
                id = 0,
                title = "Чизбургер",
                imageUrl = "burger.png",
                ingredients = emptyList(),
                method = emptyList(),
                isFavorite = false,
            ),
        onRecipeClick = {}
    )
}