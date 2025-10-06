import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.ScreenHeader

@Composable
fun FavoriteScreen(){
    @StringRes val favoriteTitle = R.string.favorites_title
    val favoriteImage = R.drawable.bcg_favorites
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        ScreenHeader(title = stringResource(id = favoriteTitle).uppercase(), image = null)
    }
}

@Preview (
    showBackground = true,
    showSystemUi = true
)
@Composable
fun FavoritePreview(){
    FavoriteScreen()
}