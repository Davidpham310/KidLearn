package com.example.kidlearn.presentation.common.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CustomLoadingSpinner(modifier: Modifier = Modifier, color: Color = Color.Blue) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing)
        )
    )

    Canvas(modifier = modifier.size(48.dp)) {
        val strokeWidth = 8f
        val radius = size.minDimension / 2 - strokeWidth

        // Vẽ vòng tròn trống màu xám nhạt
        drawCircle(
            color = color.copy(alpha = 0.2f),
            radius = radius,
            style = Stroke(width = strokeWidth)
        )
        // Vẽ vòng tròn nhỏ hơn, góc quay thay đổi theo angle
        drawArc(
            color = color,
            startAngle = angle,
            sweepAngle = 90f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )
    }
}
