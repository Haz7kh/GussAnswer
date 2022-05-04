package com.haz7.guesstheanswer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ShareActivity : AppCompatActivity() {
    lateinit var QuestionText: String
    lateinit var EditTextShareTitle: EditText
    lateinit var btnShare:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        EditTextShareTitle = findViewById(R.id.editTextShareTitle)
        btnShare = findViewById(R.id.BtnShareQuestion)
        QuestionText = intent.getStringExtra("Question text extra").toString()
        //call the last share title
        val sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE)
        var questionTitle = sharedPreferences.getString("share_title", "")
        EditTextShareTitle.setText(questionTitle)

        btnShare.setOnClickListener {
            onShareQuestionClicked()
        }

    }


    private fun onShareQuestionClicked() {
        // bring the text from the plainText(the title user enter)
        val questionTitle: String = EditTextShareTitle?.getText().toString()
        val shareIntent = Intent()
        //use action send to send data to apps in the phone
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(
            Intent.EXTRA_TEXT, """
     $questionTitle
     $QuestionText
     """.trimIndent()
        )
        //which kind of data do we want to share
        shareIntent.type = "text/plain"
        startActivity(shareIntent)
        val sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("share_title", questionTitle)
        editor.apply()
    }
}