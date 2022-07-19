package com.marking.markview

import android.annotation.SuppressLint
import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.markview.databinding.ActivityTestBinding
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.InetAddressUtils
import com.marking.markview.builder.RetrofitBuilder
import com.marking.markview.model.Login.LoginModel
import com.marking.markview.model.Login.LoginResponseModel
import com.marking.markview.model.SignUp.Certification.CertificationInputModel
import com.marking.markview.model.SignUp.Certification.CertificationResponseModel
import com.marking.markview.model.SignUp.Unique.CheckDuplicateModel
import com.marking.markview.model.SignUp.Unique.ResponseModel
import com.marking.markview.model.SignUp.Unique.SignUpUniqueModel
import com.marking.markview.model.SignUpResponseModel
import com.marking.markview.model.SignUpUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*


class TestActivity : Activity() {
    val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }

    var test_string : String? = null

    private var unique_key: String? = null
    private var imp_uid: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.testDanal.setOnClickListener {
            binding.phoneRegistWeb.visibility = View.VISIBLE
            initWebView()
        }

        binding.sendImpUid.setOnClickListener {
            InputImpUid()
        }

        binding.sendResultUid.setOnClickListener {
            InputUniqueKey()
        }
        binding.sendDuplicateUid.setOnClickListener {
            CheckIdDuplicate()
        }
        binding.signUp.setOnClickListener {
            SignUp()
        }
        binding.loginBtn.setOnClickListener {
            Login()
        }
        binding.testBtn.setOnClickListener {

            println("null 아니면 테스트 성공 : $test_string")

        }

    }

    private fun Login() { //로그인
        val user_id = "kyutae0523"
        val password = "tongview123!"
        val login_ip = getIPAddress(true)

        println(login_ip)

        val data = LoginModel(user_id, password, login_ip)
        RetrofitBuilder.api.getLoginInfo(data).enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                val duplicate = response.body()
                println("duplicate?.data")
                println(duplicate?.data)
                println(response)
                println("duplicate?.data")

                test_string = duplicate?.data.toString()

            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }


    private fun SignUp() { //아이디 중복체크
        val user_id = "kyutae0523"
        val name = "김규태"
        val password = "tongview123!"
        val email = "kyutae0523@gmail.com"
        val birthday = "1996-05-23"
        val phone = "01032269252"
        val gender = "M"
        val unique_key =
            "5Y4ylX5LVKjZ3MtpX6WaVbfSERjvZOMzYzpwHlcsMF1zWJGfa0p4fVTvekW5qy+q9KsSYuFfebbBC/3qe8+hJg=="
        val department = "kyutae0523"
        val zip_code = "kyutae0523"
        val address = "kyutae0523"
        val data = SignUpUserModel(
            user_id,
            name,
            password,
            email,
            birthday,
            phone,
            gender,
            unique_key,
            department,
            zip_code,
            address
        )
        RetrofitBuilder.api.getSignInfo(data).enqueue(object : Callback<SignUpResponseModel> {
            override fun onResponse(
                call: Call<SignUpResponseModel>,
                response: Response<SignUpResponseModel>
            ) {
                val duplicate = response.body()
                println("duplicate?.data")
                println(duplicate)
                println(duplicate?.data)
                println(response)
                println("duplicate?.data")

            }

            override fun onFailure(call: Call<SignUpResponseModel>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }

    private fun CheckIdDuplicate() { //아이디 중복체크
        val user_id = "kyutae0523"
        val data = CheckDuplicateModel(user_id)
        RetrofitBuilder.api.Duplicate(data).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val duplicate = response.body()
                println("duplicate?.data")
                println(duplicate?.data)
                println(response)
                println("duplicate?.data")

            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }


    private fun InputUniqueKey() { //impuid input, name,birth, gender, phone, uniquekey response
        println("unique_keyunique_key ${unique_key}")
//        5Y4ylX5LVKjZ3MtpX6WaVbfSERjvZOMzYzpwHlcsMF1zWJGfa0p4fVTvekW5qy+q9KsSYuFfebbBC/3qe8+hJg==
        val data = SignUpUniqueModel(unique_key)
        RetrofitBuilder.api.getUserData(data).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val userInfo = response.body()
                println("userInfo?.data")
                println(userInfo?.data)
                println(response)
                println("userInfo?.data")

            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }


    private fun InputImpUid() { //impuid input, name,birth, gender, phone, uniquekey response
        println("inputImpuid : $imp_uid")
        val data = CertificationInputModel("imp_263090934205")
        RetrofitBuilder.api.getCertification(data)
            .enqueue(object : Callback<CertificationResponseModel> {
                override fun onResponse(
                    call: Call<CertificationResponseModel>,
                    response: Response<CertificationResponseModel>
                ) {
                    val userInfo = response.body()
                    println(userInfo?.data?.unique_key)
                    unique_key = userInfo?.data?.unique_key

                }

                override fun onFailure(call: Call<CertificationResponseModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                }
            })
    }

    //region Functions
    /** 휴대폰 인증 화면 (Danal Certification API) */
    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView() {
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
        @android.webkit.JavascriptInterface
        fun getData(impUid: String) {
            println("impUid")
            println(impUid)
            println("impUid")
            imp_uid = impUid
        }
    }


    fun getIPAddress(useIPv4: Boolean): String {
        try {
            val interfaces: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                val addrs: List<InetAddress> =
                    Collections.list(intf.inetAddresses)
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress) {
                        val sAddr = addr.hostAddress.toUpperCase()
                        val isIPv4 = InetAddressUtils.isIPv4Address(sAddr)
                        if (useIPv4) {
                            if (isIPv4) return sAddr
                        } else {
                            if (!isIPv4) {
                                val delim =
                                    sAddr.indexOf('%') // drop ip6 port suffix
                                return if (delim < 0) sAddr else sAddr.substring(0, delim)
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}

