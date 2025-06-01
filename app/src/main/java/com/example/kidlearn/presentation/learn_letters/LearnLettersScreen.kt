package com.example.kidlearn.presentation.learn_letters
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kidlearn.core.components.icons.AppIcons
import com.example.kidlearn.core.theme.Blue
import com.example.kidlearn.presentation.common.component.LearningCard
import com.example.kidlearn.presentation.common.component.RotatingImage
import com.example.kidlearn.presentation.common.component.TopBar
import java.util.Locale

@Composable
fun LearnLettersScreen(
    navController: NavHostController,
    viewModel: LetterViewModel,
    id: String
) {
    val context = LocalContext.current
    val letter = viewModel.letter.value

    // TTS instance
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    DisposableEffect(Unit) {
        tts = TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) tts?.language = Locale("vi") // Tiếng Việt
        }

        onDispose {
            tts?.shutdown()
        }
    }

    LaunchedEffect(key1 = id) {
        viewModel.fetchLetter(id)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopBar(
                onBackClick = { navController.popBackStack() },
                title = "Học chữ cái",
                backgroundColor = Blue
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (letter != null) {
                LearningCard(
                    title = letter.title.toString(),
                    illustration = letter.imageRes,
                    onPlayAudio = {
                        tts?.speak(letter.textToRead, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                )
            } else {
                // Hiển thị loading spinner khi dữ liệu chưa tải xong
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    RotatingImage(imageRes = AppIcons.RefreshIcon, modifier = Modifier.size(48.dp))
                }
            }
        }
    }
}
