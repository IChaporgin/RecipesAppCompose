package ru.ichaporgin.recipesappcompose.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.theme.recipesAppTypography

@Composable
fun ScreenHeader(
    title: String,
    image: String?,
    defaultImageRes: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(224.dp)
    ) {
        if (image != null) {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                placeholder = painterResource(R.drawable.img_placeholder),
                error = painterResource(R.drawable.img_error),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(defaultImageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(
                text = title.uppercase(),
                style = recipesAppTypography.displayLarge,
                modifier = Modifier
                    .padding(all = 10.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ScreenHeaderPreview() {
    ScreenHeader(title = "Категории".uppercase(), image = null, defaultImageRes = R.drawable.bcg_categories)
}