package com.progneo.smarthealth.presentation.screen.notsupported

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.progneo.smarthealth.R
import com.progneo.smarthealth.presentation.theme.WearAppTheme

@Composable
fun NotSupportedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.HeartBroken,
            contentDescription = stringResource(id = R.string.broken_heart_description),
            tint = Color.Red
        )
        Text(
            text = stringResource(id = R.string.not_available),
            textAlign = TextAlign.Center
        )
    }
}

@WearPreviewDevices
@Composable
fun NotSupportedScreenPreview() {
    WearAppTheme {
        NotSupportedScreen()
    }
}
