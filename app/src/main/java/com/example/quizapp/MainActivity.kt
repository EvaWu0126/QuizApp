package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = "MainActivity"
    }

    lateinit var quiz : Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadQuestions()

        // load questions from JSON (we will learn how to do this next class)
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }
        Log.d(TAG, "onCreate: jsonString $jsonString")

        val gson = Gson()
        val qType = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonString, qType)
        Log.d(TAG, "loadQuestions: $questions")

        quiz = Quiz(questions)

        // next steps:
        // make your Question data class
        // use this tutorial: https://medium.com/@hissain.khan/parsing-with-google-gson-library-in-android-kotlin-7920e26f5520
        // scroll down to "Parsing between a Collection, List, or Array"
        // convert your jsonString to a List<Question>
        // log that list of questions to see if it worked

        // create a Quiz object and pass in that list of questions
        // as a parameter

        // do the initial question & answer choices setup

        // set listeners to react to user input
        // passing info to and from the Quiz object

    }

    private fun loadQuestions() {
        // load questions from JSON (we will learn how to do this next class)
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }
        Log.d(TAG, "onCreate: jsonString $jsonString")
    }

}