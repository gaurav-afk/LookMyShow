package com.towerofapp.lookmyshow.ui.components

import android.R.attr.maxWidth
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.towerofapp.lookmyshow.ui.components.model.HallLayout
import com.towerofapp.lookmyshow.ui.components.model.Seat


@Composable
fun HallScreen() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth

        Spacer(
            modifier = Modifier
                .padding(bottom = 65.dp)
                .width(screenWidth * 0.8f)
                .height(8.dp)
                .background(Color(0XFF78d0ff))
                .align(Alignment.BottomCenter),
        )
        Box(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .width(screenWidth * 0.8f)
                .height(25.dp)
                .background(Color.Gray, shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "SCREEN",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}