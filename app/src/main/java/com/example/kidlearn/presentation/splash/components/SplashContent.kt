package com.example.kidlearn.presentation.splash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kidlearn.R
import com.example.kidlearn.core.components.icons.AppIcons
import com.example.kidlearn.core.theme.*

@Composable
fun SplashContent(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFB24592), Color(0xFFF15F79))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            // Icon sách
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_book),
                    contentDescription = null,
                    tint = Color(0xFF8E24AA),
                    modifier = Modifier.size(60.dp)
                )
            }

            Text(
                text = "Học Vui",
                style = AppTypography.titleLarge.copy(
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Học tập thật vui vẻ",
                style = AppTypography.bodyLarge.copy(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SplashIcon(AppIcons.ColorIcon, Blue, 0)
                SplashIcon(AppIcons.AppleIcon, Green, 1)
                SplashIcon(AppIcons.NumberIcon, Yellow, 2)
                SplashIcon(AppIcons.RainbowIcon, Orange, 3)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Progress bar + text
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .width(240.dp)
                        .height(12.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.3f))
                ) {
                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White,
                        trackColor = Color.Transparent
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Đang tải...",
                    color = Color.White,
                    style = AppTypography.bodyLarge
                )
            }
        }
    }
}

