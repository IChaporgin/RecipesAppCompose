import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.ui.ScreenHeader
import ru.ichaporgin.recipesappcompose.ui.categories.CategoryItem

@Composable
fun Categories() {
    val categoriesTitle = "Категории"
    val categoriesImage = R.drawable.bcg_categories
    val categoryItemTitle = "Бургеры"
    val categoryItemDescription = "Рецепты всех популярных бургеров"
    val categoryItemImage = R.drawable.burger

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
        ) {
            ScreenHeader(
                categoriesTitle,
                categoriesImage,
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                CategoryItem(categoryItemTitle, categoryItemDescription, categoryItemImage)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun CategoriesPreview() {
    Categories()
}