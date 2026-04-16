package com.example.sokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the view by use of their ids
        val txtname = findViewById<TextView>(R.id.txtProductName)
        val txtcost = findViewById<TextView>(R.id.txtProductCost)
        val txtdesc = findViewById<TextView>(R.id.txtProductDescription)
        val imgProduct = findViewById<ImageView>(R.id.imgProduct)

//        Retrieve the data passed rom the previous activity(MainActivity)
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost", 0)
        val desc = intent.getStringExtra("product_description")
        val product_photo = intent.getStringExtra("product_photo")

//        Update the textview with the data passed from the previous activity
        txtname.text = name
        txtcost.text = "Kes $cost"
        txtdesc.text = desc


//        Specify the image url
        val imageUrl = "https://frostyghost23.alwaysdata.net/static/images/$product_photo"

        Glide.with(this)
            .load(imageUrl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

//        Find the EditText and the Pay Now button by use of their ids
        val phone = findViewById<EditText>(R.id.phone)
        val pay = findViewById<Button>(R.id.pay)

//        Set on click Listener
        pay.setOnClickListener {
//            Specify the API endpoint
            val api = "http://frostyghost23.alwaysdata.net/api/mpesa_payment"

//            Create a RequestParams that will hold the data to be sent to the API
            val data = RequestParams()

//            Insert data into the RequestParams
            data.put("amount", cost)
            data.put("phone", phone.text.toString().trim())

//            Import the API helper
//            Application context is passed to the helper class, it tells us what to do with the data
            val helper = ApiHelper(applicationContext)

//            Access the post function inside the helper class
            helper.post(api, data)

//            clear the EditText
            phone.text.clear()

        }

    }
}