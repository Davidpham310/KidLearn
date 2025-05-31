package com.example.kidlearn.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kidlearn.core.components.icons.AppIcons
import com.example.kidlearn.core.theme.BackgroundLight
import com.example.kidlearn.presentation.home.components.ChallengeSection
import com.example.kidlearn.presentation.home.components.HeaderSection
import com.example.kidlearn.presentation.home.components.learn.ExerciseCard
import com.example.kidlearn.presentation.home.components.learn.LearningGrid

@Composable
fun HomeScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            HeaderSection()
        }

        item {
            Text(
                text = "Học tập vui vẻ",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
        }

        item {
            LearningGrid()
        }

        item {
            ChallengeSection(
                progress = 0.45f,
                onStartClick = {
                    // TODO: Điều hướng sang màn chơi hoặc hoạt động học
                }
            )
        }

        item {
            Text(
                text = "Bài tập vui",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ExerciseCard("Câu đố", "Trả lời câu hỏi", AppIcons.QuizIcon)
                ExerciseCard("Video", "Xem video học tập", AppIcons.VideoIcon)
            }
        }
    }
}


