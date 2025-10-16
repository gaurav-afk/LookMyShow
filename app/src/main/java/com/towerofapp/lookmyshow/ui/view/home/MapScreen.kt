package com.towerofapp.lookmyshow.ui.view.home


import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.towerofapp.lookmyshow.data.model.Theater
import com.towerofapp.lookmyshow.data.model.Theaters
import com.towerofapp.lookmyshow.ui.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import kotlin.collections.isNotEmpty

@Composable
fun MapScreen(viewModel: MoviesViewModel = hiltViewModel()) {
    val theaters = viewModel.allTheaters
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    AndroidView(factory = { mapView }) { mv ->
        mv.getMapAsync { map ->
            map.setStyle(
                Style.Builder().fromUri(
                    "https://api.maptiler.com/maps/openstreetmap/style.json?key=WOAs9WNfE5SDGa3v9SUV"
                )
            ) {
                if (theaters.isNotEmpty()) {
                    theaters.forEach { theater ->
                        map.addMarker(
                            org.maplibre.android.annotations.MarkerOptions()
                                .position(LatLng(theater.latitude, theater.longitude))
                                .title(theater.name)
                        )
                    }

                    val first = theaters.first()
                    map.cameraPosition = CameraPosition.Builder()
                        .target(LatLng(first.latitude, first.longitude))
                        .zoom(12.0)
                        .build()
                }
            }
            }
        }
    }