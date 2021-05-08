 package com.noobdevs.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestions : AppCompatActivity() , View.OnClickListener
{
    private var mCurrentPosition : Int = 1
    private var mQuestion : ArrayList<Question>? = null
    private var mSelectedOption : Int = 0
    private var mCorrect : Int = 0

    lateinit var progressBar : ProgressBar
    lateinit var tv : TextView
    lateinit var tvquestion : TextView
    lateinit var ivImage : ImageView
    lateinit var tvOptionone : TextView
    lateinit var tvOptiontwo : TextView
    lateinit var tvOptionthree : TextView
    lateinit var tvOptionfour : TextView

    lateinit var submit : Button

    private var username : String? = null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz__questions)

        username = intent.getStringExtra(Constants.USERNAME)

        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        tv = findViewById<TextView>(R.id.tv_progress)
        tvquestion = findViewById<TextView>(R.id.tv_question)
        ivImage= findViewById<ImageView>(R.id.iv_image)
        tvOptionone = findViewById<TextView>(R.id.tv_option_one)
        tvOptiontwo = findViewById<TextView>(R.id.tv_option_two)
        tvOptionthree = findViewById<TextView>(R.id.tv_option_three)
        tvOptionfour = findViewById<TextView>(R.id.tv_option_four)
        mQuestion = Constants.getQuestions()
        submit = findViewById(R.id.btn_submit)
        setQuestion()

        tvOptionone.setOnClickListener(this)
        tvOptiontwo.setOnClickListener(this)
        tvOptionthree.setOnClickListener(this)
        tvOptionfour.setOnClickListener(this)

        submit.setOnClickListener(this)

    }

    private fun setQuestion()
    {
        val question = mQuestion?.get(mCurrentPosition - 1)
        defaultOption()
        if(mCurrentPosition == mQuestion!!.size)
        {
         submit.text = "Finish"
        }
        else
        {
            submit.text = "Submit"
        }
        progressBar.progress = mCurrentPosition
        tv.text = "$mCurrentPosition" + "/" + progressBar.max
        tvquestion.text = question!!.question
        ivImage.setImageResource(question.image)
        tvOptionone.text = question.optionOne
        tvOptiontwo.text = question.optionTwo
        tvOptionthree.text = question.optionThree
        tvOptionfour.text = question.optionFour

    }

    private fun defaultOption()
    {
        val options = ArrayList<TextView>()
        options.add(0 , tvOptionone)
        options.add(1 , tvOptiontwo)
        options.add(2 , tvOptionthree)
        options.add(3 , tvOptionfour)

        for(option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this ,  R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?)
    {
        when (v?.id)
        {

            R.id.tv_option_one -> {

                selectedOptionView(tvOptionone, 1)
            }

            R.id.tv_option_two -> {

                selectedOptionView(tvOptiontwo, 2)
            }

            R.id.tv_option_three -> {

                selectedOptionView(tvOptionthree, 3)
            }

            R.id.tv_option_four -> {

                selectedOptionView(tvOptionfour, 4)
            }
            R.id.btn_submit -> {
                if(mSelectedOption == 0)
                {
                    mCurrentPosition++
                    when {

                        mCurrentPosition <= mQuestion!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            val intent = Intent(this , Result::class.java)
                            intent.putExtra(Constants.USERNAME , username)
                            intent.putExtra(Constants.CORRECT_ANSWERS ,  mCorrect)
                            intent.putExtra(Constants.TOTAL_QUESTION ,  mQuestion!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else
                {
                    val question = mQuestion?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOption) {
                        answerView(mSelectedOption , R.drawable.wrong_option_border_bg)
                    }
                    else
                    {
                        mCorrect++
                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestion!!.size) {
                        submit.text = "FINISH"
                    } else {
                        submit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOption = 0
                }
            }
        }
    }

    private fun answerView(answer : Int , drawableView : Int )
    {
        when(answer)
        {
            1 -> {
                tvOptionone.background = ContextCompat.getDrawable(this , drawableView)
            }

            2 -> {
                tvOptiontwo.background = ContextCompat.getDrawable(this , drawableView)
            }

            3 -> {
                tvOptionthree.background = ContextCompat.getDrawable(this , drawableView)
            }

            4 -> {
                tvOptionfour.background = ContextCompat.getDrawable(this , drawableView)
            }
        }

    }

    private fun selectedOptionView(tv:TextView , selectedOption : Int )
    {
        defaultOption()
        mSelectedOption = selectedOption

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface , Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this ,  R.drawable.selected_option_border_bg)

    }
}