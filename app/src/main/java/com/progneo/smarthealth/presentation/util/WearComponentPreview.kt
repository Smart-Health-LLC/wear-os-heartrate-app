package com.progneo.smarthealth.presentation.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    apiLevel = 33,
    uiMode = Configuration.UI_MODE_TYPE_WATCH,
    device = Devices.WEAR_OS_SMALL_ROUND
)
annotation class WearComponentPreview
