package edu.robertconstantin.animeappcliient.feature_heroes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import edu.robertconstantin.animeappcliient.core.ui.theme.LocalSpacing
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO

@Composable
fun HeroItem(
    imageLoader: ImageLoader,
    hero: HeroVO
) {

    val dimens = LocalSpacing.current
    Box(
        modifier = Modifier
            .height(400.dp)
            .clickable {

            },
        contentAlignment = Alignment.BottomStart
    ) {
        //SUFACE 1 for adding the iamge as background using suerface.
        Surface(shape = RoundedCornerShape(size = dimens.spaceSmall)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(model = "http://10.0.2.2:8001${hero.image}", imageLoader = imageLoader),
                contentDescription = "Hero Item",
                contentScale = ContentScale.Crop
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color =  MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = dimens.spaceSmall,
                bottomEnd = dimens.spaceSmall
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = dimens.spaceSmall)
            ) {
                Text(
                    text = hero.name,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.padding(top = dimens.spaceSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }


            }

        }

    }
}