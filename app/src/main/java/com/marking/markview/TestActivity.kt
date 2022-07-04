package com.marking.markview

import android.annotation.SuppressLint
import android.os.Bundle
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.*
import androidx.core.view.get
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_general.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import com.example.markview.R
import com.example.markview.databinding.ActivityTestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TestActivity : Activity() {
    val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        binding.testDanal.setOnClickListener {
            binding.phoneRegistWeb.visibility = View.VISIBLE
            initWebView()
        }


    }

    //region Functions
    /** 휴대폰 인증 화면 (Danal Certification API) */
    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView(){
        val settings = binding.phoneRegistWeb.settings
        settings.javaScriptEnabled = true
        settings.builtInZoomControls = true
        settings.loadWithOverviewMode = true
        settings.defaultTextEncodingName = "UTF-8"

        binding.phoneRegistWeb.webChromeClient = WebChromeClient()
        binding.phoneRegistWeb.webViewClient = WebViewClient()
        binding.phoneRegistWeb.addJavascriptInterface(JsHandler(), "Android")
        binding.phoneRegistWeb.loadUrl("file:///android_asset/test.html")
    }

    inner class JsHandler {
        val API_URL = "https://api.iamport.kr/users/getToken/"
        var retrofit : Retrofit
        var iamportClient : IamportClient

        init {
            retrofit = Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build()
            iamportClient = retrofit.create(IamportClient::class.java)
        }

        @android.webkit.JavascriptInterface
        fun getData(impUid : String) {
            val apiKey : String = getString(R.string.iamportAPIKey)
            val apiSecretKey : String = getString(R.string.iamportAPISecretKey)
            val getData = iamportClient.token(AuthData(apiKey, apiSecretKey))
            getData.enqueue(object : Callback<AccessToken> {
                override fun onResponse(call: Call<AccessToken>?, response: Response<AccessToken>?) {
                    if (response!!.isSuccessful) {
                        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                        println("토큰은? ${response.body()!!.response?.accessToken}")
                        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                        val token = response.body()?.response?.accessToken
                        val getAuth = iamportClient.certification_by_imp_uid(token!!, impUid)
                        getAuth.enqueue(object : Callback<Certification> {
                            override fun onResponse(
                                call: Call<Certification>?,
                                response: Response<Certification>?
                            ) {
                                if (response!!.isSuccessful) {
                                    println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                                    println("인증정보 ${response.body()?.response}")
                                    println("이름정보 ${response.body()?.response?.name}")
                                    println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                                    binding.phoneRegistWeb.visibility = View.GONE
                                    binding.nameIconColor.visibility = View.VISIBLE
                                    binding.phoneIconColor.visibility = View.VISIBLE
                                    binding.nameChangeColor.text = "본인인증완료"
                                    binding.nameChangeColor.setTextColor(ContextCompat.getColor(this@RegistrationActivity, R.color.be_marktong))
                                    binding.etRegistrationName.text = response.body()?.response?.name.toString()
                                    binding.etRegistrationPhone.text = response.body()?.response?.phone.toString()
                                }
                            }

                            override fun onFailure(call: Call<Certification>, t: Throwable) {
                            }
                        })
                    }
                }

                override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                }

            })
        }
    }
}

