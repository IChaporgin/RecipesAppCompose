package ru.ichaporgin.recipesappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.ui.theme.RecipesAppComposeTheme
import ru.ichaporgin.recipesappcompose.ui.theme.recipesAppTypography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesAppComposeTheme {
                Scaffold(
                   content = {padding: PaddingValues ->
                       Box (
                           modifier = Modifier
                               .fillMaxSize(),
                           contentAlignment = Alignment.Center
                       ) {
                           Column(Modifier
                               .padding(padding)
                               .padding(16.dp),
                               verticalArrangement = Arrangement.spacedBy(16.dp),
                           ) {
                               TestTypography()
                           }
                       }
                   }
                )
            }
        }
    }
}

@Composable
fun TestTypography() {
    Text(
        text = "Display Large",
        style = recipesAppTypography.displayLarge
    )
    Text(
        text = "Title Medium",
        style = recipesAppTypography.titleMedium
    )
    Text(
        text = "Body Medium",
        style = recipesAppTypography.bodyMedium
    )
    Text(
        text = "Body Small",
        style = recipesAppTypography.bodySmall
    )
    Text(
        text = "Label Large",
        style = recipesAppTypography.labelLarge
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipesAppComposeTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                TestTypography()
            }
        }
    }
}