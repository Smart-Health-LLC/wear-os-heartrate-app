package com.progneo.smarthealth.service.sleep

import androidx.wear.protolayout.ResourceBuilders
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders
import com.google.android.horologist.tiles.SuspendingTileService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow

private const val RESOURCES_VERSION = "0"

@AndroidEntryPoint
class GoToSleepTileService : SuspendingTileService() {

    @Inject
    lateinit var renderer: GoToSleepTileRenderer

    private lateinit var tileStateFlow: StateFlow<GoToSleepTileState?>

    override suspend fun resourcesRequest(
        requestParams: RequestBuilders.ResourcesRequest
    ): ResourceBuilders.Resources {
        return ResourceBuilders.Resources.Builder()
            .setVersion(RESOURCES_VERSION)
            .build()
    }

    override suspend fun tileRequest(
        requestParams: RequestBuilders.TileRequest
    ): TileBuilders.Tile {
        val tileState = GoToSleepTileState(isSleeping = false)
        return renderer.renderTimeline(tileState, requestParams)
    }
}
