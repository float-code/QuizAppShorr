package com.example.quizapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var questionTextView : TextView;
    lateinit var buttonA : Button;
    lateinit var buttonB : Button;
    lateinit var buttonC : Button;
    lateinit var buttonD : Button;
    lateinit var buttonE : Button;
    lateinit var buttonF : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun wireWidgets(){
            questionTextView = findViewById(R.id.text_view_question_main)
            buttonA = findViewById(R.id.button_a_vertical)
            buttonB = findViewById(R.id.button_b_vertical)
            buttonC = findViewById(R.id.button_c_vertical)
            buttonD = findViewById(R.id.button_d_vertical)
            buttonE = findViewById(R.id.button_e_vertical)
            buttonF = findViewById(R.id.button_f_vertical)
        }

        wireWidgets()

        val gson = Gson()
        val inputStream = resources.openRawResource(R.raw.quiz)
        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }
        val quizData = gson.fromJson(jsonString, QuizTest::class.java)
        val quizQuestions = quizData.questions
        val quizChoices = quizData.choices
        val quizValues = quizData.values

        val quizClass = Quiz(quizQuestions, quizChoices, quizValues)

        val allButtons : List<Button> = listOf(buttonA, buttonB, buttonC, buttonD, buttonE, buttonF)

        fun loadButtons(){
            var amountOfButtons = quizClass.getAmountOfButtonsToLoad()
            for(i in 0..(allButtons.size-1)){
                if (i >= amountOfButtons){
                    break
                }
                val currentChoice = quizChoices.get(quizClass.currentQuestion).get(i)
                allButtons.get(i).setText(currentChoice)
                allButtons.get(i).visibility = View.VISIBLE
            }
        }

        fun resetButtonsVisibility(){
            for(i in 0..(allButtons.size-1)){
                allButtons.get(i).visibility = View.GONE
            }
        }

        fun loadQuestion(){
            questionTextView.setText(quizClass.getCurrentQuestion())
        }

        fun goToNextQuestion(){
            if (quizClass.getCurrentQuestion() == "End"){
                //display sscore and end
                var score : String = quizClass.calculateScore()
                questionTextView.setText(score)
                Log.d("MAINACTIVITY", "questionTextView set to score")
            }else{
                quizClass.advanceQuestion()
                resetButtonsVisibility()
                loadQuestion()
                loadButtons()
            }

        }

        buttonA.setOnClickListener {
            quizClass.updateScore(0)
            goToNextQuestion()
        }
        buttonB.setOnClickListener {
            quizClass.updateScore(1)
            goToNextQuestion()
        }
        buttonC.setOnClickListener {
            quizClass.updateScore(2)
            goToNextQuestion()
        }
        buttonD.setOnClickListener {
            quizClass.updateScore(3)
            goToNextQuestion()
        }
        buttonE.setOnClickListener {
            quizClass.updateScore(4)
            goToNextQuestion()
        }
        buttonF.setOnClickListener {
            quizClass.updateScore(5)
            goToNextQuestion()
        }

        loadQuestion()
        loadButtons()

    }
}