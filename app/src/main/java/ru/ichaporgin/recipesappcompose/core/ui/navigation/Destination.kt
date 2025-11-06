package ru.ichaporgin.recipesappcompose.core.ui.navigation

sealed class Destination(val route: String) {
    object Category : Destination("category")
    object Recipes : Destination("recipes/{categoryId}") {
        fun createRoute(categoryId: Int) =
            "recipes/$categoryId"
    }
    object RecipeDetails : Destination("recipe/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe/$recipeId"
    }

    object Favorite : Destination("favorites")
}