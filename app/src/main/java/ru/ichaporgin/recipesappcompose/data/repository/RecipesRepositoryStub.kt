package ru.ichaporgin.recipesappcompose.data.repository

import android.content.Context
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import ru.ichaporgin.recipesappcompose.data.model.CategoryDto
import ru.ichaporgin.recipesappcompose.data.model.RecipeDto

object RecipesRepositoryStub {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun getCategories(context: Context): List<CategoryDto> {
        return context.assets.open("category.json").use { inputStream ->
            val categoryJson = inputStream
                .bufferedReader()
                .use { it.readText() }
            json.decodeFromString(ListSerializer(CategoryDto.serializer()), categoryJson)
        }
    }

    fun getRecipesByCategoryId(context: Context, categoryId: Int?): List<RecipeDto> {
        val recipesJson = context.assets.open("recipe.json")
            .use {
                it
                    .bufferedReader()
                    .readText()
            }
        val recipes: List<RecipeDto> = json.decodeFromString(
            ListSerializer(RecipeDto.serializer()),
            recipesJson
        )
        return recipes.filter { it.categoryId == categoryId }
    }
}
