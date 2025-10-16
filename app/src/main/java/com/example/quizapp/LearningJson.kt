package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson

class LearningJson : AppCompatActivity() {

    companion object {
        val TAG = "LearningJSON"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_learning_json)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gson = Gson()
        val inputStream = resources.openRawResource(R.raw.pluslife)
        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }
        val test = gson.fromJson(jsonString, PluslifeTest::class.java)
        val testData5 = test.testData
        val tempSamples = testData5.temperatureSamples
        val samples5 = testData5.samples
        
        //Log.d(TAG, "onCreate: $testData5")
        //Log.d(TAG, "onCreate: $tempSamples")
        val targetTemp = test.targetTemp

        var maxTemp : Float = 0F
        var minTemp : Float = Float.MAX_VALUE
        var avgTemp : Float= 0F
        var tempAmount : Float = 0F
        var largestTempDifference : Float = 0F
        var largestTempDifferenceIndex = 0
        var totalTempAboveThreshold : Int = 0

        for(i in 0..(tempSamples.size-1)){
            var currentTestSample = tempSamples.get(i)
            var time = currentTestSample.time
            var temp = currentTestSample.temp

            if (temp > maxTemp){
                maxTemp = temp
            }
            if (temp < minTemp) {
                minTemp = temp
            }
            avgTemp = avgTemp+temp
            tempAmount = tempAmount+1

            if (Math.abs(temp-targetTemp) >= largestTempDifference){
                largestTempDifference = (Math.abs(temp-targetTemp.toFloat()))
                largestTempDifferenceIndex = i
            }
            if (Math.abs(temp-targetTemp) >= 0.5){
                totalTempAboveThreshold+=1
            }
        }

        for (i in 0..(samples5.size-1)){
            val currentSample5 = samples.get(i)
            if (sample.startingChannel == 3){

            }
        }



        avgTemp = avgTemp/tempAmount

        Log.d(TAG, "maxTemp: $maxTemp")
        Log.d(TAG, "minTemp: $minTemp")
        Log.d(TAG, "avgTemp: $avgTemp")
        Log.d(TAG, "largestTempDifference: $largestTempDifference")
        Log.d(TAG, "largestTempDifferenceIndex: $largestTempDifferenceIndex")
        Log.d(TAG, "totalTempAboveThreshold: $totalTempAboveThreshold")

        

        // examples of for loops in java vs kotlin
        // assume that we have List<TempSample> called tempSamples
        // in java, an enhanced for loop would look like:
        // for(TempSample sample: tempSamples)
        // in kotlin
        // for(sample in tempSamples)
        // in java, a traditional for loop...
        // for(int i=0; i< tempSamples.size; i++){
        // tempSamples[i]

//        val tempSamples = listOf(1,2,3)
//        for(i in 0..(tempSamples.size-1)){
//
//        }
//        for (i in 0 until < tempSamples.size){
//
//        }
//        for(i in tempSamples.indices){
//            tempSamples[i]
//        }

    }
}