package ru.ichaporgin.recipesappcompose.core.ui.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.ichaporgin.recipesappcompose.Constants
import ru.ichaporgin.recipesappcompose.R
import ru.ichaporgin.recipesappcompose.core.ui.theme.recipesAppTypography

@Composable
fun CategoryItem(
    title: String,
    description: String,
    image: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(156.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() },
    ) {
        Column {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                placeholder = painterResource(R.drawable.img_placeholder),
                error = painterResource(R.drawable.img_error),
                contentScale = ContentScale.Crop
            )

            Text(
                text = title.uppercase(),
                style = recipesAppTypography.titleMedium,
                modifier = Modifier
                    .padding(all = 8.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = description,
                style = recipesAppTypography.bodySmall,
                modifier = Modifier
                    .padding(all = 8.dp),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 3
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false
)
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        title = "Бургеры",
        description = "Самые сочные бургеры",
        image = "burger.png",
        onClick = {}
    )
}