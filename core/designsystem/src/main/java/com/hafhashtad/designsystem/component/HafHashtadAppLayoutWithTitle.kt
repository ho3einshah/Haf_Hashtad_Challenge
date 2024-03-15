package com.hafhashtad.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.hafhashtad.designsystem.theme.HafHashtadCodeChallengeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HafHashtadAppLayoutWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
    ),
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = contentHorizontalAlignment,
    ) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = title, modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = colors
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            content()
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    apiLevel = 33
)
private fun HafHashtadTopAppBarWithLeftTitleLightPreview() {
    HafHashtadCodeChallengeTheme {

        HafHashtadAppLayoutWithTitle(
            title = "haf hashtad",
        ) {}

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    apiLevel = 33
)
private fun HafHashtadTopAppBarWithLeftTitleDarkPreview() {
    HafHashtadCodeChallengeTheme {
        HafHashtadAppLayoutWithTitle(
            title = "haf hashtad",
        ) {

        }
    }
}