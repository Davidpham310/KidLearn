package com.example.kidlearn.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.kidlearn.core.components.icons.AppIcons
import com.example.kidlearn.core.theme.AppTypography
import com.example.kidlearn.core.theme.Blue
import com.example.kidlearn.domain.model.LearnableItem

@Composable
fun LargeItemCard(
    item: LearnableItem,
    isLoading: Boolean,
    onSpeakClick: () -> Unit,
    fontSize: TextUnit = 80.sp
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CustomLoadingSpinner()
            } else {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.displayTitle,
                            style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold , fontSize = fontSize),
                            color = Blue
                        )
                        Image(
                            painter = rememberAsyncImagePainter(item.imageRes),
                            contentDescription = item.displayTitle,
                            modifier = Modifier.size(80.dp).border(1.dp, Color.Black)
                        )
                    }

                    IconButton(onClick = onSpeakClick) {
                        Icon(
                            painter = painterResource(id = AppIcons.SoundIcon),
                            contentDescription = "Phát âm",
                            tint = Color.Black,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun ItemsGrid(
    items: List<LearnableItem>,
    isLoading: Boolean,
    onItemClick: (String) -> Unit,
    fontSize: TextUnit = 16.sp
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        if (isLoading && items.isEmpty()) {
            items(6) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .height(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomLoadingSpinner()
                    }
                }
            }
        } else {
            items(items.size) { index ->
                val singleItem = items[index]

                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable {
                            onItemClick(singleItem.id)
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = singleItem.displayTitle,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = fontSize,
                                fontWeight = FontWeight.SemiBold
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

