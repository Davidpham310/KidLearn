package com.example.kidlearn.presentation.learn_numbers

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kidlearn.core.theme.Blue
import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.presentation.common.component.ItemsGrid
import com.example.kidlearn.presentation.common.component.LargeItemCard
import com.example.kidlearn.presentation.common.component.TopBar
import com.example.kidlearn.presentation.learn_letters.LetterEvent
import com.example.kidlearn.presentation.learn_letters.LetterViewModel
import java.util.Locale

@Composable
fun LearnNumbersScreen(
    navController: NavHostController,
    viewModel: NumberViewModel,
    id: String
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val currentNumber = state.currentNumber
    val numbers = state.values

    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    // Khởi tạo TextToSpeech và gán hàm nói vào ViewModel
    LaunchedEffect(Unit) {
//        viewModel.uploadNumbersToFirestoreSequentially()
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("vi")
                viewModel.onSpeak = { text ->
                    tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            tts?.shutdown()
            viewModel.onSpeak = null
        }
    }

    LaunchedEffect(id) {
        viewModel.fetchNumber(id)
        viewModel.fetchAllNumber()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF8DC)) // Màu nền dịu
        ) {
            TopBar(
                onBackClick = { navController.popBackStack() },
                title = "Học Chữ Số",
                backgroundColor = Blue
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                state.errorMessage != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = state.errorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                else -> {
                    val currentItem = currentNumber?.let {
                        LearnableItem.Number(
                            id = it.id,
                            value = it.displayTitle,
                            imageRes = it.imageRes,
                            textToRead = it.textToRead
                        )
                    }

                    currentItem?.let { item ->
                        LargeItemCard(
                            item = item,
                            isLoading = state.isLoading,
                            onSpeakClick = {
                                viewModel.onEvent(NumberEvent.PlayAudio(item.textToRead))
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Chọn Chữ Số",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Blue
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val gridItems = numbers.map {
                        LearnableItem.Number(
                            id = it.id,
                            value = it.displayTitle,
                            imageRes = it.imageRes,
                            textToRead = it.textToRead
                        )
                    }

                    ItemsGrid(
                        items = gridItems,
                        isLoading = state.isLoading && numbers.isEmpty(),
                        onItemClick = { selectedId ->
                            viewModel.onEvent(NumberEvent.SelectNumber(selectedId))
                        }
                    )
                }
            }
        }
    }
}