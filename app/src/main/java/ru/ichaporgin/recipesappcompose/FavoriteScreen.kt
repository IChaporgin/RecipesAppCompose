import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.ui.ScreenHeader

@Composable
fun Favorite(){
    val favoriteTitle = "Избранное"
    val favoriteImage = R.drawable.bcg_favorites
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        ScreenHeader(title = favoriteTitle, image = favoriteImage)
    }
}

@Preview (
    showBackground = true,
    showSystemUi = true
)
@Composable
fun FavoritePreview(){
    Favorite()
}