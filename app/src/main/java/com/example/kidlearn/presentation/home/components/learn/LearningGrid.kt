package com.example.kidlearn.presentation.home.components.learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kidlearn.core.components.icons.AppIcons
import com.example.kidlearn.core.theme.Blue
import com.example.kidlearn.core.theme.Green
import com.example.kidlearn.core.theme.Orange
import com.example.kidlearn.core.theme.Yellow
import com.example.kidlearn.presentation.home.model.LearningItem

@Composable
fun LearningGrid() {
    val items = listOf(
        LearningItem("Chữ cái", "Học bảng chữ cái", AppIcons.LetterIcon, Blue),
        LearningItem("Số đếm", "Học đếm số", AppIcons.NumberIcon, Green),
        LearningItem("Màu sắc", "Học màu sắc", AppIcons.ColorIcon, Yellow),
        LearningItem("Con vật", "Học về động vật", AppIcons.ElephantIcon, Orange),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.height(300.dp)
    ) {
        items(items.size) { index ->
            LearningCard(items[index])
        }
    }
}


