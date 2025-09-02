package ru.ichaporgin.recipesappcompose.data.repository

import android.content.Context
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import ru.ichaporgin.recipesappcompose.data.model.CategoryDto
import ru.ichaporgin.recipesappcompose.data.model.RecipeDto

class RecipesRepositoryStub(
    private val context: Context
) {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun getCategories(): List<CategoryDto> {
        return  context.assets.open("category.json").use { inputStream ->
            val categoryJson = inputStream
                .bufferedReader()
                .use { it.readText() }
            json.decodeFromString(ListSerializer(CategoryDto.serializer()), categoryJson)
        }
    }

    fun getRecipesByCategoryId(id: Int): List<RecipeDto> {
        val recipesJson = context.assets.open("recipe.json").use { inputStream ->
            inputStream
                .bufferedReader()
                .use { it.readText() }
        }
        val recipes: List<RecipeDto> = json.decodeFromString(recipesJson)
        return recipes.filter { it.id == id }
    }
}
