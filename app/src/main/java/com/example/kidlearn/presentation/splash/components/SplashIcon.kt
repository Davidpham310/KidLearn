package com.example.kidlearn.presentation.splash.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun SplashIcon(
    iconRes: Int,
    bgColor: Color,
    index: Int = 0,
    isWaveAnimation: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition()
    val waveDuration = 2000 // Thời gian cho 1 chu kỳ sóng (ms)

    // Tạo animation sóng với độ trễ khác nhau cho mỗi icon
    val waveProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = waveDuration
                0f at 0 with LinearEasing
                1f at waveDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        )
    )

    // Tính toán offsetY dựa trên vị trí icon và waveProgress
    val offsetY = if (isWaveAnimation) {
        // Tạo hiệu ứng sóng bằng hàm sin
        // Mỗi icon sẽ có phase khác nhau dựa trên index
        val phase = (index * PI / 2).toFloat()
        (sin((waveProgress * 2 * PI) + phase) * 15).toFloat()
    } else 0f

    // Tạo animation scale nhẹ
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(64.dp)
            .offset(y = offsetY.dp)
            .scale(scale)
            .background(bgColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(36.dp)
        )
    }
}
