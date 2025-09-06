package ru.ichaporgin.recipesappcompose.core.ui.categories

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ichaporgin.recipesappcompose.core.ui.theme.recipesAppTypography

@Composable
fun CategoryItem(
    title: String,
    description: String,
    image: String,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val bitmap = remember(image) {
        context.assets.open(image).use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    }

    Box(
        modifier = Modifier
            .width(156.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() },
    ) {
        Column {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
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
                color = MaterialTheme.colorScheme.onSurface
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
        description = "Рецепты всех популярных бургеров",
        image = "burger.png",
        onClick = {}
    )
}