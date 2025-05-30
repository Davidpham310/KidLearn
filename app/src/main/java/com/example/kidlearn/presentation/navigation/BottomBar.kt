package com.example.kidlearn.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kidlearn.core.theme.Blue

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Blue),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Screen.bottomNavItems.forEach { screen ->
            val selected = currentRoute == screen.route

            Box(
                modifier = Modifier
                    .weight(1f) // chia đều các item
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (selected) Color.White else Color.Transparent)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (!selected) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = screen.icon!!),
                        contentDescription = screen.label,
                        modifier = Modifier.size(24.dp),
                        tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    if (selected) {
                        Text(
                            text = screen.label!!,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .widthIn(max = 70.dp)
                                .height(16.dp)
                        )
                    } else {
                        Spacer(modifier = Modifier.height(16.dp)) // chiếm không gian giữ bố cục đều
                    }
                }
            }
        }
    }
}


