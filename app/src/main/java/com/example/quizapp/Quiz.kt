package com.example.quizapp

import com.google.gson.annotations.SerializedName

class Quiz (var questions : List<String>, var choices : List<Choice>, var values : List<Value>) {
    val quizQuestions : List<String> = questions
    val quizChoices : List<Choice> = choices
    val quizValues : List<Value> = values
    var currentQuestion : Int = 0

    var bludIndex = mutableListOf(0, 0, 0, 0)

    fun getCurrentQuestion() :String{
        if (currentQuestion <= questions.size){
            return quizQuestions.get(currentQuestion)
        }
        return "End"
    }

    fun advanceQuestion(){
        currentQuestion = currentQuestion + 1
    }

    fun getAmountOfButtonsToLoad() : Int{
        val currentChoice = quizChoices.get(currentQuestion)
        return currentChoice.choice.size
    }

    fun updateScore(choiceIndex : Int){
        val currentChoice = quizChoices.get(currentQuestion)
        bludIndex.set(choiceIndex, bludIndex.get(choiceIndex).toInt() + currentChoice.choice.get(choiceIndex).toInt())
    }
}