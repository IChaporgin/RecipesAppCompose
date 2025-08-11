package ru.ichaporgin.recipesappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.ichaporgin.recipesappcompose.ui.theme.RecipesAppComposeTheme

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
                               .fillMaxSize()
                               .padding(padding),
                           contentAlignment = Alignment.Center
                       ){
                           Text(
                               text = "Test Application",
                               modifier = Modifier.padding(padding),
                               fontSize = 30.sp
                           )
                       }
                   }
                )
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipesAppComposeTheme {

    }
}