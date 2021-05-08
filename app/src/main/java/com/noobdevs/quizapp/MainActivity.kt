package com.noobdevs.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val btnStart = findViewById<Button>(R.id.btn_start)
        val etName = findViewById<EditText>(R.id.et)
        btnStart.setOnClickListener {


            if (etName.text.toString().isEmpty()) {

                Toast.makeText(this@MainActivity, "Please enter your name", Toast.LENGTH_SHORT)
                    .show()
            }
            else
            {

                val intent = Intent(this@MainActivity, QuizQuestions::class.java)
                intent.putExtra(Constants.USERNAME , etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}