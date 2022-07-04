package com.marking.markview

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import com.example.markview.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        member_btn.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://markview.kr/join_clause.php"))
            startActivity(intent)
        }

    }

}