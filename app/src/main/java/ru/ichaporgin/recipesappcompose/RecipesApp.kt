import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.ui.theme.RecipesAppTheme
import ru.ichaporgin.recipesappcompose.ui.theme.recipesAppTypography

@Composable
fun RecipesApp(){
    RecipesAppTheme {
        Scaffold { padding: PaddingValues ->
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        Modifier
                            .padding(padding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        TestTypography()
                        ThemeTestScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun TestTypography() {
    Text(
        text = "Display Large",
        style = recipesAppTypography.displayLarge,
        color = MaterialTheme.colorScheme.primary
    )
    Text(
        text = "Title Medium",
        style = recipesAppTypography.titleMedium,
        color = MaterialTheme.colorScheme.secondary
    )
    Text(
        text = "Body Medium",
        style = recipesAppTypography.bodyMedium,
        color = MaterialTheme.colorScheme.tertiary
    )
    Text(
        text = "Body Small",
        style = recipesAppTypography.bodySmall,
        color = MaterialTheme.colorScheme.background
    )
    Text(
        text = "Label Large",
        style = recipesAppTypography.labelLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun ThemeTestScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TestTypography()

        Text(
            text = "Primary color",
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Secondary color",
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = "Tertiary color",
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = "Background color",
            color = MaterialTheme.colorScheme.background
        )
    }
}