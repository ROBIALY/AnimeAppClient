package edu.robertconstantin.animeappcliient.feature_heroes.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.robertconstantin.animeappcliient.core.ui.theme.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandartTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 100,
    maxLines: Int = 4,
    singleLine: Boolean = true,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(LocalSpacing.current.spaceSmall),
    leadingIcon: ImageVector? = null,
    leadingIconSize: Dp = 24.dp,
    keyBoardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit = {}
) {
    val dimens = LocalSpacing.current
    Column(modifier = modifier.padding(dimens.spaceSmall)) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(dimens.spaceSmall),
            shape = roundedCornerShape
        ) {
            TextField(
                modifier = modifier,
                value = text,
                onValueChange = {
                    if (it.length <= maxLength) onValueChange(it)
                },
                singleLine = singleLine,
                maxLines = maxLines,
                placeholder = { Text(text = hint) },
                keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
                leadingIcon = if (leadingIcon != null) {
                    {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(leadingIconSize)
                        )
                    }
                } else null
            )

        }
    }

}