package com.hafhashtad.ui


import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hafhashtad.designsystem.theme.HafHashtadWidgetSize

@Composable
fun Loading(modifier: Modifier = Modifier) {


    CircularProgressIndicator(
        modifier = modifier.size(HafHashtadWidgetSize.loadingSize),
        strokeWidth = 2.dp,
        color = MaterialTheme.colorScheme.primary
    )
}