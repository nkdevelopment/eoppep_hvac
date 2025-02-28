package nkdevelopment.net.hvac_questions

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nkdevelopment.net.hvac_questions.ui.theme.HVAC_QuestionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HVAC_QuestionsTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Θέματα ΕΟΠΠΕΠ",
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
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App title and description
                Text(
                    text = "Θέματα εξετάσεων πιστοποίησης ΕΟΠΠΕΠ: Τεχνικός εγκαταστάσεων Ψύξης, Αερισμού και Κλιματισμού",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Δείτε όλες τις ερωτήσεις και τις απαντήσεις σε όλα τα θέματα εξετάσεων πιστοποίησης ΕΟΠΠΕΠ για την ειδικότητα: Τεχνικός εγκαταστάσεων ψύξης, αερισμού και κλιματισμού.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Part A button
                Button(
                    onClick = {
                        val intent = Intent(context, QuestionsActivity::class.java)
                        intent.putExtra("jsonFile", "Part_A_1_90.json")
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Questions",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        "Ερωτήσεις Μέρος Α",
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Part B button
                Button(
                    onClick = {
                        val intent = Intent(context, QuestionsActivity::class.java)
                        intent.putExtra("jsonFile", "Part_B_1_210.json")
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Questions",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        "Ερωτήσεις Μέρος Β",
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // About button
                Button(
                    onClick = { showDialog.value = true },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        "Σχετικά με την εφαρμογή",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Σχετικά με την εφαρμογή") },
            text = {
                Column {
                    Text("Η εφαρμογή σας δίδει τη δυνατότητα να δείτε τις απαντήσεις σε όλες τις ερωτήσεις πιστοποίησης αρχικής επαγγελματικής κατάρτισης της ειδικότητας ΣΑΕΚ: Τεχνικός εγκαταστάσεων ψύξης, αερισμού και κλιματισμού.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Version: 1.0")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Developed by NK Development")
                }
            },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("Close")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HVAC_QuestionsTheme {
        MainScreen()
    }
}