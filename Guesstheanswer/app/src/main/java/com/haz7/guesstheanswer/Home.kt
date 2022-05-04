package com.haz7.guesstheanswer

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {
    lateinit var welcome:TextView
    lateinit var playBtn:Button
    lateinit var image:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        //animation
        val ttb=AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
        val stb=AnimationUtils.loadAnimation(this,R.anim.scale_to_big)


        welcome=findViewById(R.id.welcomeText)
        playBtn=findViewById(R.id.BtnStart)
        image=findViewById(R.id.imageView)

        //set the anim
        welcome.startAnimation(ttb)
        playBtn.startAnimation(ttb)
        image.startAnimation(stb)


        playBtn.setOnClickListener {
            val intent = Intent(this@Home, QuestionActivity::class.java)
            startActivity(intent)
        }


    }
    }