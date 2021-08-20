package com.example.markview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView


class IntroActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val markmImage: ImageView = findViewById(R.id.mark_m)
        val markaImage: ImageView = findViewById(R.id.mark_a)
        val markrImage: ImageView = findViewById(R.id.mark_r)
        val markkImage: ImageView = findViewById(R.id.mark_k)
        val markviewImage : ImageView = findViewById(R.id.mark_view)
        val marklensImage : ImageView = findViewById(R.id.mark_lens)
        val marktext : TextView = findViewById(R.id.mark_text)

        val markmAnimation = AnimationUtils.loadAnimation(this, R.anim.mark_m_splash_ball)
        val markaAnimation = AnimationUtils.loadAnimation(this, R.anim.mark_a_splash_ball)
        val markrAnimation = AnimationUtils.loadAnimation(this, R.anim.mark_r_splash_ball)
        val markkAnimation = AnimationUtils.loadAnimation(this, R.anim.mark_k_splash_ball)
        val markviewAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash_fadein)

        markmImage.startAnimation(markmAnimation)
        markaImage.startAnimation(markaAnimation)
        markrImage.startAnimation(markrAnimation)
        markkImage.startAnimation(markkAnimation)
        markviewImage.startAnimation(markviewAnimation)
        marklensImage.startAnimation(markviewAnimation)
        marktext.startAnimation(markviewAnimation)



        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500) // 3000 is the delayed time in milliseconds.
    }
}