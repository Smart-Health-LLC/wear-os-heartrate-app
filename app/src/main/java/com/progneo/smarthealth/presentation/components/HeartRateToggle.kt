package com.progneo.smarthealth.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.ToggleChipDefaults
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.progneo.smarthealth.PERMISSION
import com.progneo.smarthealth.R
import com.progneo.smarthealth.presentation.theme.WearAppTheme
import com.progneo.smarthealth.presentation.util.WearComponentPreview

@Composable
fun HeartRateToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    permissionState: PermissionState,
    modifier: Modifier = Modifier
) {
    ToggleChip(
        modifier = modifier,
        checked = checked,
        colors = ToggleChipDefaults.toggleChipColors(),
        onCheckedChange = { enabled ->
            if (permissionState.status.isGranted) {
                onCheckedChange(enabled)
            } else {
                permissionState.launchPermissionRequest()
            }
        },
        label = { Text(stringResource(id = R.string.heart_rate_toggle)) },
        toggleControl = {
            Icon(
                imageVector = ToggleChipDefaults.switchIcon(checked),
                contentDescription = stringResource(id = R.string.heart_rate_toggle)
            )
        }
    )
}

@WearComponentPreview
@Composable
fun HeartRateTogglePreview() {
    val permissionState = object : PermissionState {
        override val permission = PERMISSION
        override val status: PermissionStatus = PermissionStatus.Granted
        override fun launchPermissionRequest() {}
    }
    WearAppTheme {
        HeartRateToggle(
            checked = true,
            onCheckedChange = {},
            permissionState = permissionState
        )
    }
}
