package com.example.quizapp

import android.view.View

class Quiz(val question: List<Question>){

    // variables to track: score, current question
    var score : Int = 0
    var current : Int = 0

    // functions
    // are there more questions?
    // checking answer
    // getting the current question
    // optional: reset the quiz, shuffle questions

    fun getCurrentQuestion(): Question {
        return question[current]
    }

    override fun toString(): String {
        return "Quiz(score=$score, current=$current)"
    }

    fun checkQuestionNum() : Boolean{
        if(current >= question.size){
            return true
        }
        return false
    }
}