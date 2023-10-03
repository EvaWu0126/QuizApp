package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = "MainActivity"
    }

    //variables
    lateinit var quiz : Quiz
    lateinit var currentQuestion: Question

    //visible widget
    lateinit var quizQuestionText : TextView
    lateinit var choice1 : Button
    lateinit var choice2 : Button
    lateinit var choice3 : Button
    lateinit var choice4 : Button
    lateinit var testNumber : TextView
    lateinit var resultText : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidget()
        loadQuestions()

    }

    // load questions from JSON (we will learn how to do this next class)
    private fun loadQuestions() {
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
        currentQuestion = quiz.getCurrentQuestion()
        quizQuestionText.text = currentQuestion.question
        choice1.text = currentQuestion.choice[0]
        choice2.text = currentQuestion.choice[1]
        choice3.text = currentQuestion.choice[2]
        choice4.text = currentQuestion.choice[3]

        // set listeners to react to user input
        choice1.setOnClickListener {
            // pass the answer to the quiz object
            //Log.d(TAG, "before: $quiz ${quiz.getCurrentQuestion()}")
            quiz.score = quiz.score + currentQuestion.answer[0]
            testNumber.text = quiz.score.toString()
            //Log.d(TAG, "addPoints: ${quiz.score}")
            //Log.d(TAG, "after: $quiz ${quiz.getCurrentQuestion()}")
            if (quiz.checkQuestionNum()){
                quizResultScreen()
            }else{
                quiz.current++
                genQuizQues()
            }

        }
        choice2.setOnClickListener {
            // pass the answer to the quiz object
            quiz.score = quiz.score + currentQuestion.answer[1]
            testNumber.text = quiz.score.toString()
            //Log.d(TAG, "addPoints: ${quiz.score}")
            if (quiz.checkQuestionNum()){
                quizResultScreen()
            }else{
                quiz.current++
                genQuizQues()
            }
        }
        choice3.setOnClickListener {
            // pass the answer to the quiz object
            quiz.score = quiz.score + currentQuestion.answer[2]
            testNumber.text = quiz.score.toString()
            //Log.d(TAG, "addPoints: $quiz.score")
            if (quiz.checkQuestionNum()){
                quizResultScreen()
            }else{
                quiz.current++
                genQuizQues()
            }
        }
        choice4.setOnClickListener {
            // pass the answer to the quiz object
            quiz.score = quiz.score + currentQuestion.answer[3]
            testNumber.text = quiz.score.toString()
            //Log.d(TAG, "addPoints: $quiz.score")
            if (quiz.checkQuestionNum()){
                quizResultScreen()
            }else{
                quiz.current++
                genQuizQues()
            }
        }

        // passing info to and from the Quiz object
    }


    private fun wireWidget(){
        quizQuestionText = findViewById(R.id.text_main_question)
        choice1 = findViewById(R.id.button_main_choice1)
        choice2 = findViewById(R.id.button_main_choice2)
        choice3 = findViewById(R.id.button_main_choice3)
        choice4 = findViewById(R.id.button_main_choice4)
        testNumber = findViewById(R.id.textView_main_test)
        resultText = findViewById(R.id.textView_main_resultText)
    }

    fun genQuizQues(){
        currentQuestion = quiz.getCurrentQuestion()
        quizQuestionText.text = currentQuestion.question
        choice1.text = currentQuestion.choice[0]
        choice2.text = currentQuestion.choice[1]
        choice3.text = currentQuestion.choice[2]
        choice4.text = currentQuestion.choice[3]
    }

    fun quizResultScreen(){
        quizQuestionText.visibility = View.GONE
        choice1.visibility = View.GONE
        choice2.visibility = View.GONE
        choice3.visibility = View.GONE
        choice4.visibility = View.GONE
        resultText.visibility = View.VISIBLE
    }
}