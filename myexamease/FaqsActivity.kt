package com.example.myexamease

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FaqsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs)

        val backBtn2 = findViewById<Button>(R.id.backBtn2)

        backBtn2.setOnClickListener {
            // Finish the current activity to go back
            finish()
        }
    }
}