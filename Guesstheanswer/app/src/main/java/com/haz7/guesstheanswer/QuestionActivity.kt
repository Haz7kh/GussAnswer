package com.haz7.guesstheanswer

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import kotlin.random.Random

class QuestionActivity : AppCompatActivity() {



    lateinit var question: TextView
    lateinit var trueBtn: Button
    lateinit var falseBtn: Button
    lateinit var refresh: ImageButton
    lateinit var shareQuestion: ImageButton
    lateinit var score:TextView

    //the winnerFragment :
    val winner:FragmentManager=supportFragmentManager
    val winnerTr:FragmentTransaction=winner.beginTransaction()
    val winnerFragment=WinnerFragment()




    val QuestionsList: ArrayList<String> = arrayListOf(
    )
    val Answers: ArrayList<Boolean> = arrayListOf(

    )

    val AnswersDetails: ArrayList<String> = arrayListOf(
    )
    lateinit var CurrentQuestion:String
    lateinit var CurrentAnswerDetails:String
    var CurreentAnswer:Boolean = false
    var scoreCount=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        question = findViewById(R.id.textViewQuestion)
        trueBtn = findViewById(R.id.btnStart)
        falseBtn = findViewById(R.id.btnFalse)
        refresh = findViewById(R.id.btnChangeQuastion)
        shareQuestion = findViewById(R.id.btnSHare)
        score = findViewById(R.id.scoreNumber)


        showQuestion()

        refresh.setOnClickListener{
            showQuestion()
            scoreCount = scoreCount-5
            score.text= scoreCount.toString()
        }

        trueBtn.setOnClickListener {
            onTrueClicked()
        }
        falseBtn.setOnClickListener {
            onFalseClick()
        }
        shareQuestion.setOnClickListener {
            onShareQuestionClicked()
        }

    }


    private fun showQuestion() {
        var json:String?=null
        try {
            var input: InputStream = assets.open("questions_new.json")
            json = input.bufferedReader().use { it.readText() }
            var jasonArray= JSONArray(json)
            for (i in 0 until jasonArray.length()){
                var jsonoj = jasonArray.getJSONObject(i)
                QuestionsList.add(jsonoj.getString("question"))
                Answers.add(jsonoj.getBoolean("A"))
                AnswersDetails.add(jsonoj.getString("A_D"))


            }
        }catch (e: IOException){

        }




        for (i in 0..QuestionsList.size){
            var index = Random.nextInt(QuestionsList.size)


            CurrentQuestion = QuestionsList[index]
            CurreentAnswer = Answers[index]
            CurrentAnswerDetails = AnswersDetails[index]
            question.setText(CurrentQuestion)
        }




    }
    private fun onTrueClicked(){

        if (CurreentAnswer == true){
            Toast.makeText(this,"Right answer",Toast.LENGTH_SHORT).show()
            scoreCount = scoreCount+10
            score.text= scoreCount.toString()

            showQuestion()
            winner()

        }else{
            Toast.makeText(this,"Wrong answer",Toast.LENGTH_SHORT).show()
            scoreCount = scoreCount-10
            score.text= scoreCount.toString()
            val intent = Intent(this@QuestionActivity, AnswerActivity::class.java)
            intent.putExtra("QuestionAnswer", CurrentAnswerDetails)
            startActivity(intent)

            showQuestion()
            winner()
        }


    }
    private fun onFalseClick(){
        if (CurreentAnswer == false){
            Toast.makeText(this,"Right answer",Toast.LENGTH_SHORT).show()
            scoreCount = scoreCount+10
            score.text= scoreCount.toString()
            showQuestion()
            winner()

        }else{
            Toast.makeText(this,"Wrong answer",Toast.LENGTH_SHORT).show()
            scoreCount = scoreCount-10
            score.text= scoreCount.toString()
            val intent = Intent(this@QuestionActivity, AnswerActivity::class.java)
            intent.putExtra("QuestionAnswer", CurrentAnswerDetails)
            startActivity(intent)

            showQuestion()
            winner()
        }

    }
    private fun onShareQuestionClicked() {
        scoreCount = scoreCount-15
        score.text= scoreCount.toString()
        val intent = Intent(this@QuestionActivity, ShareActivity::class.java)
        intent.putExtra("Question text extra", CurrentQuestion)
        startActivity(intent)
    }
    fun winner(){
        if(scoreCount>=50){
            val bundle=Bundle()
            bundle.putInt("Scour",scoreCount)
            winnerFragment.arguments = bundle
            winnerTr.add(R.id.QuestionLayout,winnerFragment)
            winnerTr.commit()
//            var alertDialog=AlertDialog.Builder(this@QuestionActivity)
//            alertDialog.setTitle("YOU ARE SMART AND WINNER :)").setMessage("Do you want to play again?")
//                .setIcon(R.drawable.ic_win_done_24)
//                . setCancelable(false)
//                .setNegativeButton("NO", DialogInterface.OnClickListener { dialogInterface, which ->
//                    dialogInterface.cancel()
//                    finish()
//                })
//                .setPositiveButton("YES", DialogInterface.OnClickListener { dialogInterface, which ->
//                    scoreCount=0
//                    score.text= scoreCount.toString()
//                })
//                alertDialog.create().show()

        }

    }


}