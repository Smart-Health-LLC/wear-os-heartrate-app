package com.progneo.smarthealth.presentation.screen.heartrate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.progneo.smarthealth.PERMISSION
import com.progneo.smarthealth.presentation.components.HeartRateCard
import com.progneo.smarthealth.presentation.components.HeartRateToggle
import com.progneo.smarthealth.presentation.theme.WearAppTheme

@Composable
fun HeartRateScreen(
    hrValue: Double,
    hrEnabled: Boolean,
    onEnableClick: (Boolean) -> Unit,
    permissionState: PermissionState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeartRateToggle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            checked = hrEnabled,
            onCheckedChange = onEnableClick,
            permissionState = permissionState
        )
        HeartRateCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            heartRate = hrValue
        )
    }
}

@WearPreviewDevices
@Composable
fun PassiveDataScreenPreview() {
    val permissionState = object : PermissionState {
        override val permission = PERMISSION
        override val status: PermissionStatus = PermissionStatus.Granted
        override fun launchPermissionRequest() {}
    }
    WearAppTheme {
        HeartRateScreen(
            hrValue = 65.6,
            hrEnabled = true,
            onEnableClick = {},
            permissionState = permissionState
        )
    }
}
