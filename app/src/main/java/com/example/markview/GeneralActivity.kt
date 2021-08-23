package com.example.markview

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_general.*
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception


class GeneralActivity : Activity() {

    val Gallery = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)

        letter_toggle.setOnCheckedChangeListener(MyCheckedChangeListener())
        image_toggle.setOnCheckedChangeListener(MyCheckedChangeListener())
        image_toggle_btn.setOnClickListener { loadImage() }


        var data = resources.getStringArray(R.array.category)
        var data0 = resources.getStringArray(R.array.select)
        var data1 = resources.getStringArray(R.array.select_1)
        var data2 = resources.getStringArray(R.array.select_2)
        var data3 = resources.getStringArray(R.array.select_3)
        var data4 = resources.getStringArray(R.array.select_4)
        var data5 = resources.getStringArray(R.array.select_5)
        var data6 = resources.getStringArray(R.array.select_6)
        var data7 = resources.getStringArray(R.array.select_7)
        var data8 = resources.getStringArray(R.array.select_8)
        var data9 = resources.getStringArray(R.array.select_9)
        var data10 = resources.getStringArray(R.array.select_10)
        var data11 = resources.getStringArray(R.array.select_11)
        var data12 = resources.getStringArray(R.array.select_12)
        var data13 = resources.getStringArray(R.array.select_13)
        var data14 = resources.getStringArray(R.array.select_14)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        var adapter0 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data0)
        var adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data1)
        var adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data2)
        var adapter3 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data3)
        var adapter4 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data4)
        var adapter5 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data5)
        var adapter6 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data6)
        var adapter7 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data7)
        var adapter8 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data8)
        var adapter9 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data9)
        var adapter10 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data10)
        var adapter11 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data11)
        var adapter12 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data12)
        var adapter13 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data13)
        var adapter14 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data14)
        spinner.adapter = adapter
        spinner2.adapter = adapter0
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        spinner2.adapter = adapter0
                    }
                    1 -> {
                        spinner2.adapter = adapter1
                    }
                    2 -> {
                        spinner2.adapter = adapter2
                    }
                    3 -> {
                        spinner2.adapter = adapter3
                    }
                    4 -> {
                        spinner2.adapter = adapter4
                    }
                    5 -> {
                        spinner2.adapter = adapter5
                    }
                    6 -> {
                        spinner2.adapter = adapter6
                    }
                    7 -> {
                        spinner2.adapter = adapter7
                    }
                    8 -> {
                        spinner2.adapter = adapter8
                    }
                    9 -> {
                        spinner2.adapter = adapter9
                    }
                    10 -> {
                        spinner2.adapter = adapter10
                    }
                    11 -> {
                        spinner2.adapter = adapter11
                    }
                    12 -> {
                        spinner2.adapter = adapter12
                    }
                    13 -> {
                        spinner2.adapter = adapter13
                    }
                    14 -> {
                        spinner2.adapter = adapter14
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


    }

    inner class MyCheckedChangeListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

            when (buttonView?.id) {
                R.id.letter_toggle ->
                    if (isChecked) {
                        letter_toggle_text.visibility = View.VISIBLE
                        letter_toggle_edit_img.visibility = View.VISIBLE
                        letter_toggle_edit_text.visibility = View.VISIBLE
                        spinner.visibility = View.VISIBLE
                        spinner2.visibility = View.VISIBLE
                        lets_check.visibility = View.VISIBLE
                        image_toggle_btn.visibility = View.INVISIBLE
                        image_toggle_text.visibility = View.INVISIBLE
                        btn_image.visibility = View.INVISIBLE
                        invisible_lens.visibility = View.INVISIBLE
                        image_toggle.isChecked = false
                    }
                R.id.image_toggle ->
                    if (isChecked) {
                        image_toggle_text.visibility = View.VISIBLE
                        image_toggle_btn.visibility = View.VISIBLE
                        btn_image.visibility = View.VISIBLE
                        spinner.visibility = View.VISIBLE
                        spinner2.visibility = View.VISIBLE
                        lets_check.visibility = View.VISIBLE
                        letter_toggle_text.visibility = View.INVISIBLE
                        letter_toggle_edit_text.visibility = View.INVISIBLE
                        letter_toggle_edit_img.visibility = View.INVISIBLE
                        invisible_lens.visibility = View.INVISIBLE
                        letter_toggle.isChecked = false
                    }
            }
        }
    }

    private fun loadImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Gallery) {
            if (resultCode == RESULT_OK) {
                var dataUri = data?.data
                try {
                    var bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    btn_image.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "권한설정을 해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun general_back_btn(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun lets_check(view: View) {
        val intent = Intent(this, CheckActivity::class.java)
        startActivity(intent)
    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }

}