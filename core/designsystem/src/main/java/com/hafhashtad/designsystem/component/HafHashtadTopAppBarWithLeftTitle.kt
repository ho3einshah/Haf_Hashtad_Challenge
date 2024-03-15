package com.hafhashtad.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HafHashtadTopAppBarWithLeftTitle(
    modifier: Modifier = Modifier,
    title: String,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
    ),
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title, modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        },
        colors = colors
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun HafHashtadTopAppBarWithLeftTitleLightPreview() {
    HafHashtadTopAppBarWithLeftTitle(
        title = "haf hashtad",
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun HafHashtadTopAppBarWithLeftTitleDarkPreview() {
    HafHashtadTopAppBarWithLeftTitle(
        title = "haf hashtad",
    )
}