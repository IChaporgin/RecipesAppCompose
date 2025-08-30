package ru.ichaporgin.recipesappcompose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
)