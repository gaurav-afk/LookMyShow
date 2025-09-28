package com.towerofapp.lookmyshow.ui.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

import com.towerofapp.lookmyshow.data.model.Theater

@Composable
fun FindScreen(theaters: List<Theater>) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(28.6139, 77.2090), // Delhi center
            11f
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        theaters.forEach { theater ->
            Marker(
                state = MarkerState(position = LatLng(theater.latitude, theater.longitude)),
                title = theater.name,
                snippet = theater.address
            )
        }
    }
}
