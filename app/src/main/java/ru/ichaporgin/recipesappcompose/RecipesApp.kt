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
import ru.ichaporgin.recipesappcompose.ui.theme.RecipesAppTheme

@Composable
fun RecipesApp() {
    RecipesAppTheme {
        var currentScreen by remember { mutableStateOf(ScreenId.CATEGORY) }

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
                    ScreenId.CATEGORY -> Categories()
                    ScreenId.FAVORITE -> Favorite()
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