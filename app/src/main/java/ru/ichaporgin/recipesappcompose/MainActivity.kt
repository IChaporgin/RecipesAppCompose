package ru.ichaporgin.recipesappcompose

import RecipesApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.ichaporgin.recipesappcompose.ui.theme.RecipesAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesAppTheme {
                RecipesApp()
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun GreetingPreview() {
    RecipesAppTheme(darkTheme = true, dynamicColor = false) {
        RecipesApp()
    }
}