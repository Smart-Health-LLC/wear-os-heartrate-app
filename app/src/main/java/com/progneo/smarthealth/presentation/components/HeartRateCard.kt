package com.progneo.smarthealth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.progneo.smarthealth.R
import com.progneo.smarthealth.presentation.theme.WearAppTheme
import com.progneo.smarthealth.presentation.util.WearComponentPreview
import kotlin.math.roundToInt

@Composable
fun HeartRateCard(
    heartRate: Double,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {},
        enabled = false,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(id = R.string.heart_description),
                tint = Color.Red,
                modifier = Modifier.padding(end = 8.dp)
            )
            Column {
                val hrText = if (heartRate.isNaN()) "--" else heartRate.roundToInt().toString()
                Text(hrText)
                Text(
                    text = stringResource(id = R.string.last_measured),
                    style = MaterialTheme.typography.caption3
                )
            }
        }
    }
}

@WearComponentPreview
@Composable
fun HeartRateCardPreview() {
    WearAppTheme {
        HeartRateCard(66.3)
    }
}
