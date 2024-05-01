package com.example.myexamease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val regBtn = findViewById<Button>(R.id.regBtn)
        val aboutBtn = findViewById<Button>(R.id.aboutBtn)
        val faqBtn = findViewById<Button>(R.id.faqBtn)
        val userManualBtn = findViewById<Button>(R.id.userManualBtn)
        val userAccountBtn = findViewById<ImageButton>(R.id.userAccountBtn)


        regBtn.setOnClickListener {
            // Handle Registration Button Click
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        aboutBtn.setOnClickListener {
            // Handle About Us Button Click
            startActivity(Intent(this, AboutUsActivity::class.java))
        }

        faqBtn.setOnClickListener {
            // Handle FAQ Button Click
            startActivity(Intent(this, FaqsActivity::class.java))
        }

        userManualBtn.setOnClickListener {
            // Handle User Manual Button Click
            startActivity(Intent(this, UserManualActivity::class.java))
        }

        userAccountBtn.setOnClickListener {
            // Create an Intent to navigate to UserProfileActivity
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
