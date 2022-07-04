package com.marking.markview

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.markview.R
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_check.*
import kotlinx.android.synthetic.main.check_result.*
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class CheckActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)


        if (intent.hasExtra("productname")) {
            result_texty.text = intent.getStringExtra("productname")
        } else {
            Toast.makeText(this, "에러", Toast.LENGTH_SHORT).show()
        }


        btn1.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("판별 이미지")
            var v1 = layoutInflater.inflate(R.layout.check_result, null)
            builder.setView(v1)
            builder.setIcon(R.drawable.star)
            builder.setMessage("상태 : 등록\n한글상표명 : 스타벅스\n영문상표명 : \n출원번호 : 40-1997-0070889\n출원일 : 1997-03-12\n출원인 : 스타벅스 코포레이션 (519981085753)\n상품분류 : 30")




            builder.show()
        }

    }


}
