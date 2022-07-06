package com.marking.markview

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.example.markview.R
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.InetAddressUtils
import com.marking.markview.`interface`.RetrofitAPI
import com.marking.markview.builder.RetrofitBuilder
import com.marking.markview.model.SignUpUserModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.*
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*


class LoginActivity : Activity() {

    private lateinit var mRetrofit: Retrofit
    private lateinit var mRetrofitapi: RetrofitAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        login_btn.setOnClickListener {
//            RetrofitBuilder.api.getSignInfo().enqueue(object : Callback<SignUpUserModel> {
//                override fun onResponse(call: Call<SignUpUserModel>, response: Response<SignUpUserModel>) {
//                    val userInfo = response.body()
//                    Log.d("response", "${userInfo?.user_id} ${userInfo?.password} ${userInfo?.name}")
//                }
//                override fun onFailure(call: Call<SignUpUserModel>, t: Throwable) {
//                    Log.d("error", t.message.toString())
//                }
//            })

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        member_btn.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }

    }



}