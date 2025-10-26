import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.ScreenHeader
import ru.ichaporgin.recipesappcompose.core.ui.categories.CategoryItem
import ru.ichaporgin.recipesappcompose.core.ui.model.toUiModel
import ru.ichaporgin.recipesappcompose.data.repository.RecipesRepositoryStub

@Composable
fun CategoriesScreen(
    onCategoryClick: (Int) -> Unit,
) {
    val context = LocalContext.current
    val repository = RecipesRepositoryStub
    @StringRes val categoriesTitle = R.string.category_title
    val categories = repository.getCategories(context)
        .map { it.toUiModel() }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            ScreenHeader(
                stringResource(categoriesTitle).uppercase(),
                null,
                R.drawable.bcg_categories
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories.size) { index ->
                val category = categories[index]
                CategoryItem(
                    title = category.title,
                    description = category.description,
                    image = category.imageUrl,
                    onClick = {
                        Log.d(
                            "CategoriesScreen",
                            "Clicked category: ${category.id} - ${category.title}"
                        )
                        onCategoryClick(category.id)
                    },
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen({})
}