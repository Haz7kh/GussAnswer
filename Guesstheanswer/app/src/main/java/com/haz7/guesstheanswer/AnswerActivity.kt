package com.haz7.guesstheanswer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {
    lateinit var textViewAnswer:TextView
    lateinit var BackBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        textViewAnswer = findViewById(R.id.textViewAnswer)
        BackBtn = findViewById(R.id.BtnStart)
        val answer = getIntent().getStringExtra("QuestionAnswer")
        if (answer !=null){
            textViewAnswer.setText(answer)
        }
        BackBtn.setOnClickListener {
            onReturn()
        }

    }

    private fun onReturn() {
        //end this activity and start the main ((old activity))
       finish()
    }


}