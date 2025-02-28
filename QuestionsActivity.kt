package nkdevelopment.net.hvac_questions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nkdevelopment.net.hvac_questions.ui.theme.HVAC_QuestionsTheme

class QuestionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonFile = intent.getStringExtra("jsonFile") ?: "Part_A_1_90.json"

        setContent {
            HVAC_QuestionsTheme {
                QuestionsScreen(jsonFile)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(jsonFile: String = "Part_A_1_90.json") {
    val context = LocalContext.current
    val questions = loadQuestionsFromAsset(context, jsonFile)

    val title = when(jsonFile) {
        "Part_B_1_210.json" -> "Ερωτήσεις Μέρος Β"
        else -> "Ερωτήσεις Μέρος Α"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        title,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                itemsIndexed(questions) { index, question ->
                    QuestionItem(question, index + 1)
                    if (index < questions.size - 1) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionItem(question: Question, questionNumber: Int) {
    var isAnswerVisible by remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = { isAnswerVisible = !isAnswerVisible },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp) // Less rounded corners
        ) {
            Text(
                text = "Q$questionNumber: ${question.question}",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isAnswerVisible) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 8.dp),
                shape = RoundedCornerShape(4.dp) // Less rounded corners for the card too
            ) {
                Text(
                    text = question.answer,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}