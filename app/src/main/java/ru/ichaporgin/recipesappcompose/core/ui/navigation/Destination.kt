package ru.ichaporgin.recipesappcompose.core.ui.navigation

sealed class Destination(val route: String) {
    object Category : Destination("category")
    object Recipes : Destination("recipes/{categoryId}") {
        fun createRoute(categoryId: Int) =
            "recipes/$categoryId"
    }
    object Ingredients : Destination("recipe_detail")

    object Favorite : Destination("favorites")
}