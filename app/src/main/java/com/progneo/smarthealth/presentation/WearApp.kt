package com.progneo.smarthealth.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.progneo.smarthealth.presentation.screen.heartrate.HeartRateScreen
import com.progneo.smarthealth.presentation.screen.notsupported.NotSupportedScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WearApp(
    viewModel: WearAppViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        timeText = { TimeText() }
    ) {
        val hrValue by viewModel.hrValue.collectAsState()
        val hrEnabled by viewModel.hrEnabled.collectAsState()
        val uiState by viewModel.uiState

        if (uiState == MainUiState.Supported) {
            val permissionState = rememberPermissionState(
                permission = "android.permission.BODY_SENSORS",
                onPermissionResult = { granted ->
                    if (granted) {
                        viewModel.toggleEnabled()
                    }
                }
            )
            HeartRateScreen(
                hrValue = hrValue,
                hrEnabled = hrEnabled,
                onEnableClick = { viewModel.toggleEnabled() },
                permissionState = permissionState
            )
        } else if (uiState == MainUiState.NotSupported) {
            NotSupportedScreen()
        }
    }
}
