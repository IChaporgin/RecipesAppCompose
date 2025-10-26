import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.ichaporgin.recipesappcompose.ScreenId
import ru.ichaporgin.recipesappcompose.core.ui.theme.RecipesAppTheme
import ru.ichaporgin.recipesappcompose.features.recipes.ui.RecipesScreen

@Composable
fun RecipesApp() {
    RecipesAppTheme {
        var currentScreen by remember { mutableStateOf(ScreenId.CATEGORY) }
        var selectedCategoryId by remember { mutableStateOf<Int?>(null) }
        var selectedCategoryTitle by remember { mutableStateOf<String?>(null) }
        var selectedCategoryImage by remember { mutableStateOf<String?>(null) }

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    onCategoriesClick = { currentScreen = ScreenId.CATEGORY },
                    onFavoriteClick = { currentScreen = ScreenId.FAVORITE }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (currentScreen) {
                    ScreenId.CATEGORY -> CategoriesScreen(
                        onCategoryClick  = { categoryId, categoryTitle, categoryImage ->
                            selectedCategoryId = categoryId
                            selectedCategoryTitle = categoryTitle
                            selectedCategoryImage = categoryImage
                            currentScreen = ScreenId.RECIPES
                        }
                    )
                    ScreenId.FAVORITE -> FavoriteScreen()
                    ScreenId.RECIPES -> RecipesScreen(
                        categoryId = selectedCategoryId ?: error("Category ID is required"),
                        categoryTitle = selectedCategoryTitle
                            ?: error("Category title is required"),
                        categoryImage = selectedCategoryImage ?: error("Category image is required")
                    )
                    else -> {}
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun RecipesAppPreview() {
    RecipesApp()
}