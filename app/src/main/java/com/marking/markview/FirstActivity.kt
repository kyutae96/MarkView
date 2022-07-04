package com.marking.markview

import android.os.Bundle
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
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
import com.example.markview.R


class FirstActivity : Activity() {

    private var text_name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    inner class MyCheckedChangeListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            when (buttonView?.id) {
                R.id.food_toggle ->
                    if (isChecked) {
                    } else {

                    }
                R.id.fasion_toggle ->
                    if (isChecked){

                    }
            }
        }
    }


    fun lots_check(view: View) {


        val jsonObject = JSONObject()


        var edit_text = letter_toggle_edit_text.text.toString()

        jsonObject.put("productName", edit_text)
        jsonObject.put("asignProductMainCodes", MyCheckedChangeListener())

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        GlobalScope.launch(Dispatchers.IO) {
            val url = URL("http://192.168.0.25:8002/brand/query")

            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.setRequestProperty(
                "Content-Type",
                "application/json"
            ) // The format of the content we're sending to the server
            httpURLConnection.setRequestProperty(
                "access_token",
                "bf284d40-9a76-11eb-a6b6-0800200c9a66"
            ) // The format of response we want to get from the server
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            // Send the JSON we created
            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(jsonObjectString)
            outputStreamWriter.flush()

            // Check if the connection is successful
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = httpURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                withContext(Dispatchers.Main) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(JsonParser.parseString(response))
                    Log.d("Pretty Printed JSON :", prettyJson)

                    val jsonObject = JSONObject(prettyJson)
                    val jsonArray = jsonObject.getJSONArray("data")
                    val iObject = jsonArray.getJSONObject(0)
                    text_name = iObject.getString("productName")

//                    name_text.setText(text_name)
                    val intent = Intent(applicationContext, CheckActivity::class.java)
                    intent.putExtra("productname", text_name)
                    startActivity(intent)


                }
            } else {
                Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
            }
        }
    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }

}