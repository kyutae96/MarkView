package com.example.markview

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Gallery
import android.widget.Toast
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


    }

    inner class MyCheckedChangeListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

            when (buttonView?.id) {
                R.id.letter_toggle ->
                    if (isChecked) {
                        letter_toggle_text.visibility = View.VISIBLE
                        letter_toggle_edit_img.visibility = View.VISIBLE
                        letter_toggle_edit_text.visibility = View.VISIBLE
                        image_toggle_btn.visibility = View.INVISIBLE
                        image_toggle_text.visibility = View.INVISIBLE
                        btn_image.visibility = View.INVISIBLE
                        image_toggle.isChecked = false
                    }
                R.id.image_toggle ->
                    if (isChecked) {
                        image_toggle_text.visibility = View.VISIBLE
                        image_toggle_btn.visibility = View.VISIBLE
                        btn_image.visibility = View.VISIBLE
                        letter_toggle_text.visibility = View.INVISIBLE
                        letter_toggle_edit_text.visibility = View.INVISIBLE
                        letter_toggle_edit_img.visibility = View.INVISIBLE
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


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }

}