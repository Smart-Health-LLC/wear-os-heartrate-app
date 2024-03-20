package com.progneo.smarthealth.service.sleep

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.protolayout.ColorBuilders.argb
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.material.ChipColors
import androidx.wear.protolayout.material.CompactChip
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.Typography
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import com.google.android.horologist.compose.tools.TileLayoutPreview
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import com.progneo.smarthealth.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GoToSleepTileRenderer @Inject constructor(
    @ApplicationContext context: Context
) : SingleTileLayoutRenderer<GoToSleepTileState, Unit>(context) {

    private val chipColors = ChipColors.primaryChipColors(GoToSleepTileTheme.colors)

    override fun renderTile(
        state: GoToSleepTileState,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.LayoutElement {
        return PrimaryLayout.Builder(deviceParameters)
            .setContent(
                Text.Builder(context, context.getString(R.string.tile_go_to_sleep_title))
                    .setTypography(Typography.TYPOGRAPHY_CAPTION1)
                    .setColor(argb(GoToSleepTileTheme.colors.primary))
                    .build()
            )
            .setPrimaryChipContent(
                CompactChip.Builder(
                    context,
                    context.getString(R.string.tile_button_sleep),
                    openApplicationClickable(),
                    deviceParameters
                )
                    .setChipColors(chipColors)
                    .build()
            ).build()
    }
}

@WearPreviewDevices
@Composable
fun GoToSleepTileRendererPreview() {
    val state = GoToSleepTileState(isSleeping = false)
    val context = LocalContext.current
    TileLayoutPreview(
        state = state,
        resourceState = Unit,
        renderer = GoToSleepTileRenderer(context)
    )
}
