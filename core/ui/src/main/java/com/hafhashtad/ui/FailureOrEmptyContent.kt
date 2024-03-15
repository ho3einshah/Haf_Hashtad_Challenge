package com.hafhashtad.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hafhashtad.designsystem.component.HafhashtadFilledButton
import com.hafhashtad.designsystem.icon.HafHashtadCodeChallengeIcons
import com.hafhashtad.designsystem.theme.HafHashtadCodeChallengeTheme
import com.hafhashtad.designsystem.theme.HafHashtadPadding

@Composable
fun FailureOrEmptyContent(
    modifier: Modifier = Modifier,
    message: String,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {

        Surface(
            modifier = Modifier.size(96.dp),
            shape = CircleShape
        ) {
            Icon(
                modifier = Modifier.padding(HafHashtadPadding.StandardPadding),
                painter = painterResource(
                    id = HafHashtadCodeChallengeIcons.sadFace
                ),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "No Content"
            )
        }


        Spacer(modifier = Modifier.height(HafHashtadPadding.widgetSpace))
        Text(
            text = message,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(HafHashtadPadding.widgetSpace))

        Text(
            text = stringResource(id = com.hafhashtad.designsystem.R.string.connection_error_title),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(HafHashtadPadding.widgetSpace))
        HafhashtadFilledButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                onRefreshClick()
            },
            text = {
                Text(
                    text = stringResource(id = com.hafhashtad.designsystem.R.string.retry),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.background
                )
            }
        )
    }
}


@Preview(
    name = "light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    apiLevel = 33,
    showBackground = true
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    apiLevel = 33,
    showBackground = true
)
@Composable
fun PreviewHomeTabEmpty() {
    HafHashtadCodeChallengeTheme {
        FailureOrEmptyContent(Modifier, message = "error message") {}
    }
}
