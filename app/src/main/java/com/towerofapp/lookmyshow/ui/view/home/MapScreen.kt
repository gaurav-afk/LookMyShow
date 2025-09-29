package com.towerofapp.lookmyshow.ui.view.home

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style



@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            MapView(context).apply {
                layoutParams = android.widget.FrameLayout.LayoutParams(
                    MATCH_PARENT,
                    MATCH_PARENT
                )
                getMapAsync { map ->
                    map.setStyle(
                        Style.Builder().fromUri("https://demotiles.maplibre.org/style.json")
                    )
                    map.uiSettings.isRotateGesturesEnabled = false
                }
            }
        }
    )
}
