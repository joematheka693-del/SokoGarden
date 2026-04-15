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

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find all View by use of their ids
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)

        val signupButton = findViewById<Button>(R.id.signupBtn)
        val signinTextView = findViewById<TextView>(R.id.signintxt)

//        Below when a person clicks on the Textview, they navigate to the signin activity
        signinTextView.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

//        On click of signup button, register a person

        signupButton.setOnClickListener {
//            Specify the API endpoint
            val api = "http://frostyghost23.alwaysdata.net/api/signup"

//            Create a request params to hold all data
            val data = RequestParams()

//            Add/append the username, email, password and phone to the request
            data.put("username", username.text.toString().trim())
            data.put("email", email.text.toString().trim())
            data.put("password", password.text.toString().trim())
            data.put("phone", phone.text.toString().trim())

//            import the API helper
            val helper = ApiHelper(applicationContext)

//            Inside of the helper class, access the post function and pass the api endpoint and the data
            helper.post(api, data)

            email.text.clear()
            password.text.clear()
            username.text.clear()
            phone.text.clear()

//            intent to the main activity
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)

        }


    }
}