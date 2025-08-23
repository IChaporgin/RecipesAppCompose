import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.ui.ScreenHeader

@Composable
fun Categories(){
    val categoriesTitle = "Категории"
    val categoriesImage = R.drawable.bcg_categories
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        ) {
        ScreenHeader(categoriesTitle,
            categoriesImage,)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CategoriesPreview(){
    Categories()
}