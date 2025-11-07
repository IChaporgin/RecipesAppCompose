import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.ichaporgin.recipesappcompose.Constants
import ru.ichaporgin.recipesappcompose.core.ui.model.RecipeUiModel
import ru.ichaporgin.recipesappcompose.core.ui.navigation.Destination
import ru.ichaporgin.recipesappcompose.core.ui.theme.RecipesAppTheme
import ru.ichaporgin.recipesappcompose.features.ingredient.ui.RecipeDetailScreen
import ru.ichaporgin.recipesappcompose.features.recipes.ui.RecipesScreen

@Composable
fun RecipesApp() {
    RecipesAppTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    onCategoriesClick = { navController.navigate(Destination.Category.route) },
                    onFavoriteClick = { navController.navigate(Destination.Favorite.route) }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Destination.Category.route
                ) {
                    composable(Destination.Favorite.route) { FavoriteScreen() }
                    composable(Destination.Category.route) {
                        CategoriesScreen(
                            onCategoryClick = { categoryId ->
                                navController.navigate(
                                    Destination.Recipes.createRoute(categoryId)
                                )
                            }
                        )
                    }
                    composable(
                        Destination.Recipes.route,
                        arguments = listOf(navArgument("categoryId") { type = NavType.IntType }),
                    )
                    { backStackEntry ->
                        val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
                        RecipesScreen(categoryId, navController)
                    }
                    composable(
                        Destination.RecipeDetails.route,
                        arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                    ) {
                        val recipe = navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<RecipeUiModel>(Constants.KEY_RECIPE_OBJECT)
                        recipe?.let { RecipeDetailScreen(it) }
                    }
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