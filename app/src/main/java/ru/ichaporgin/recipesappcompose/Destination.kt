package ru.ichaporgin.recipesappcompose

sealed class Destination(val route: String) {
    object Category : Destination("category")
    object Recipes : Destination("recipes/{categoryId}") {
        fun createRoute(categoryId: Int) =
            "recipes/$categoryId"
    }

    object Favorite : Destination("favorites")
}