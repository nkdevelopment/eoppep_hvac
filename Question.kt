package nkdevelopment.net.hvac_questions

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

data class Question(val question: String, val answer: String)

data class QuestionData(
    val questions: Map<String, Question>? = null
)

fun loadQuestionsFromAsset(context: Context, fileName: String = "Part_A_1_90.json"): List<Question> {
    Log.d("QuestionLoader", "Starting to load questions from asset: $fileName")

    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        Log.e("QuestionLoader", "Error opening file: $fileName", ioException)
        return emptyList()
    }

    try {
        val gson = Gson()
        // Parse directly as a map of questions
        val type = object : TypeToken<Map<String, Question>>() {}.type
        val questionsMap: Map<String, Question> = gson.fromJson(jsonString, type)

        Log.d("QuestionLoader", "Loaded ${questionsMap.size} questions from $fileName")

        // Return the values as a list
        return questionsMap.values.toList()
    } catch (e: Exception) {
        Log.e("QuestionLoader", "Error parsing JSON from $fileName", e)
        e.printStackTrace()
        return emptyList()
    }
}