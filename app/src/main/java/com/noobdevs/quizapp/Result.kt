package com.noobdevs.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Result : AppCompatActivity()
{
    lateinit var tv : TextView
    lateinit var tvScore : TextView
    lateinit var finish : Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tv = findViewById<TextView>(R.id.tv_name)
        tvScore = findViewById<TextView>(R.id.tv_score)
        finish = findViewById(R.id.btn_finish)


        val userName = intent.getStringExtra(Constants.USERNAME)
        tv.text = userName
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTION, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tvScore.text = "Your Score is $correctAnswers out of $totalQuestions."

        finish.setOnClickListener{
            startActivity(Intent(this@Result, MainActivity::class.java))
            finish()
        }
    }
}