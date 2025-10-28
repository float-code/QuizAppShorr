package com.example.quizapp

import android.util.Log
import com.google.gson.annotations.SerializedName

class Quiz (var questions : List<String>, var choices : List<List<String>>, var values : List<List<List<Int>>>) {
    val quizQuestions : List<String> = questions
    val quizChoices : List<List<String>> = choices
    val quizValues : List<List<List<Int>>> = values
    var currentQuestion : Int = 0

    var bludIndex = mutableListOf(0, 0, 0, 0)

    fun getCurrentQuestion() :String{
        if (currentQuestion < questions.size){
            return quizQuestions.get(currentQuestion)
        }
        Log.d("QUIZ", "End returned on getCurrentQuestion()")
        return "End"
    }

    fun advanceQuestion(){
        currentQuestion = currentQuestion + 1
    }

    fun getAmountOfButtonsToLoad() : Int{
        if (currentQuestion < questions.size){
            var currentChoice = quizChoices.get(currentQuestion)
            return currentChoice.size
        }
        return 0
    }

    fun updateScore(choiceIndex : Int){
        if (currentQuestion < questions.size){
//            val currentChoice = quizChoices.get(currentQuestion)
            Log.d("QUIZ", "${quizValues.get(currentQuestion)}, c : ${choiceIndex}")

            bludIndex.set(0, bludIndex.get(0).toInt() + quizValues.get(currentQuestion).get(choiceIndex).get(0))
            bludIndex.set(1, bludIndex.get(1).toInt() + quizValues.get(currentQuestion).get(choiceIndex).get(1))
            bludIndex.set(2, bludIndex.get(2).toInt() + quizValues.get(currentQuestion).get(choiceIndex).get(2))
            bludIndex.set(3, bludIndex.get(3).toInt() + quizValues.get(currentQuestion).get(choiceIndex).get(3))
        }
    }

    fun calculateScore() : String {
        var maxScore = bludIndex.max()
        var scoreIndex : Int = 0;
        if (bludIndex.get(0) == maxScore){
            scoreIndex = 0
            return "You are a Diddy blud!\n Diddy blud : ${bludIndex.get(0)},\n Aura blud : ${bludIndex.get(1)},\n Sigma blud : ${bludIndex.get(2)},\n Sigma blud : ${bludIndex.get(3)}"
        }else if (bludIndex.get(1) == maxScore){
            scoreIndex = 1
            return "You are an Aura blud!\n Diddy blud : ${bludIndex.get(0)},\n Aura blud : ${bludIndex.get(1)},\n Sigma blud : ${bludIndex.get(2)},\n Sigma blud : ${bludIndex.get(3)}"
        }else if (bludIndex.get(2) == maxScore){
            scoreIndex = 2
            return "You are a Sigma blud!\n Diddy blud : ${bludIndex.get(0)},\n Aura blud : ${bludIndex.get(1)},\n Sigma blud : ${bludIndex.get(2)},\n Sigma blud : ${bludIndex.get(3)}"
        }else if (bludIndex.get(3) == maxScore){
            scoreIndex = 3
            return "You are a Fanum blud!\n Diddy blud : ${bludIndex.get(0)},\n Aura blud : ${bludIndex.get(1)},\n Sigma blud : ${bludIndex.get(2)},\n Sigma blud : ${bludIndex.get(3)}"
        }

        return "Error"
    }
}