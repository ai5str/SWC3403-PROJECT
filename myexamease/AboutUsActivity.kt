package com.example.myexamease

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val backBtn = findViewById<Button>(R.id.backBtn)

        backBtn.setOnClickListener {
            // Finish the current activity to go back
            finish()
        }
    }
}
