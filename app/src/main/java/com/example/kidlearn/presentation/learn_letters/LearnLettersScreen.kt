package com.example.kidlearn.presentation.learn_letters

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kidlearn.core.theme.Blue
import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.presentation.common.component.*
import java.util.*
import kotlin.collections.map

@Composable
fun LearnLettersScreen(
    navController: NavHostController,
    viewModel: LetterViewModel,
    id: String
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val currentLetter = state.currentLetter
    val letters = state.letters

    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    // Khởi tạo TextToSpeech và gán hàm nói vào ViewModel
    LaunchedEffect(Unit) {
//        viewModel.uploadLettersToFirestore()
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
        viewModel.fetchLetter(id)
        viewModel.fetchAllLetters()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF8DC)) // Màu nền dịu
        ) {
            TopBar(
                onBackClick = { navController.popBackStack() },
                title = "Học Bảng Chữ Cái",
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
                    val currentItem = currentLetter?.let {
                        LearnableItem.Letter(
                            id = it.id,
                            title = it.displayTitle,
                            imageRes = it.imageRes,
                            textToRead = it.textToRead
                        )
                    }

                    currentItem?.let { item ->
                        LargeItemCard(
                            item = item,
                            isLoading = state.isLoading,
                            onSpeakClick = {
                                viewModel.onEvent(LetterEvent.PlayAudio(item.textToRead))
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Chọn Chữ Cái",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Blue
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val gridItems = letters.map {
                        LearnableItem.Letter(
                            id = it.id,
                            title = it.displayTitle,
                            imageRes = it.imageRes,
                            textToRead = it.textToRead
                        )
                    }

                    ItemsGrid(
                        items = gridItems,
                        isLoading = state.isLoading && letters.isEmpty(),
                        onItemClick = { selectedId ->
                            viewModel.onEvent(LetterEvent.SelectLetter(selectedId))
                        }
                    )
                }
            }
        }
    }
}


