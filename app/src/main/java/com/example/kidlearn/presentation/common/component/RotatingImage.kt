package com.example.kidlearn.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun RotatingImage(modifier: Modifier = Modifier, imageRes: Int) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by animateFloatAsState(
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        )
    )

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Đang tải",
        modifier = modifier.rotate(rotation)
    )
}
