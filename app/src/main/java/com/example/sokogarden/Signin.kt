package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Find the two editText, a button and Textview by use of their ids
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signupBtn = findViewById<Button>(R.id.signinBtn)
        val signuptxt = findViewById<TextView>(R.id.signuptxt)

//        On the textview set on click listener so that it redirects to the signup activity
        signuptxt.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }

//        on click of the button sign in, we need to interact with our API endpoint as we pass the two data info email and passwords
        signupBtn.setOnClickListener {

//            Specify the API endpoint
            val api = "http://frostyghost23.alwaysdata.net/api/signin"

//            Create RequestParams that will hold the data to be sent to the API
            val data = RequestParams()

//            Add/append/attach the data to be sent to the API
            data.put("email", email.text.toString())
            data.put("password", password.text.toString())

//            Import the API helper
//            Application context is passed to the helper class, it tells us what to do with the data
            val helper = ApiHelper(applicationContext)

//            By use of the function post_login inside of the helper class, post your data
            helper.post_login(api, data)


        }

        }
}