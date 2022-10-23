package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_edit_favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import edu.robertconstantin.animeappcliient.core.presentation.UiEvent
import edu.robertconstantin.animeappcliient.core.ui.theme.LocalSpacing
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.common.StandartTextField
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.util.PresentationConst.BASE_IMAGE_URL
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HeroEditFavScreen(
    imageLoader: ImageLoader,
    showUserInfo: (text: String) -> Unit = {},
    viewModel: HeroEditViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val dimens = LocalSpacing.current
    val textState = viewModel.state
    val heroImageState = viewModel.heroImgState

    LaunchedEffect(key1 = true) {
        viewModel.singleUiEvent.collectLatest { uiEvent ->
            when(uiEvent) {
                is UiEvent.ShowSnackBar -> showUserInfo(uiEvent.message.asString(context))
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface() {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(model = BASE_IMAGE_URL.plus(heroImageState), imageLoader = imageLoader),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Surface(modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth()
        ) {
            Column( modifier = Modifier.fillMaxSize()) {
                StandartTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = textState.name,
                    hint = stringResource(id = edu.robertconstantin.animeappcliient.R.string.hero_name),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = Icons.Default.Person,
                    onValueChange = {
                        viewModel.onEvent(HeroEditScreenEvent.OnNameChange(it))
                    }
                )

                Spacer(modifier = Modifier.height(dimens.spaceExtraSmall))

                StandartTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = textState.power,
                    hint = stringResource(id = edu.robertconstantin.animeappcliient.R.string.hero_power),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = Icons.Default.Description,
                    onValueChange = {
                        viewModel.onEvent(HeroEditScreenEvent.OnPowerChange(it))
                    }
                )

                Spacer(modifier = Modifier.height(dimens.spaceExtraSmall))

                StandartTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = textState.about,
                    hint = stringResource(id = edu.robertconstantin.animeappcliient.R.string.hero_description),
                    maxLines = 4,
                    singleLine = false,
                    leadingIcon = Icons.Default.Description,
                    onValueChange = {
                        viewModel.onEvent(HeroEditScreenEvent.OnAboutChange(it))
                    }
                )
                Spacer(modifier = Modifier.height(dimens.spaceExtraSmall))

                Button(
                    modifier = Modifier.fillMaxWidth().padding(dimens.spaceMedium),
                    onClick = {
                        viewModel.onEvent(HeroEditScreenEvent.OnUpdateClick)
                    }
                ) {
                    Text(text = "Update hero")
                }
            }
        }


    }

}