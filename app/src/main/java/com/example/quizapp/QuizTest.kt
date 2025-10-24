package com.example.quizapp

import com.google.gson.annotations.SerializedName

data class QuizTest(
    @SerializedName("questions") val questions : List<String>,
    @SerializedName("values") val values : List<List<List<Int>>>,
    @SerializedName("choices") val choices : List<List<String>>
)

