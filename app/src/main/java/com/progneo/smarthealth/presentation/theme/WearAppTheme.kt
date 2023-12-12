package com.progneo.smarthealth.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

@Composable
fun WearAppTheme(
    colors: Colors = initialThemeValues.colors,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}
