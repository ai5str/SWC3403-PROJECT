package com.example.myexamease

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UserManualActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_manual)

        val backBtn3 = findViewById<Button>(R.id.backBtn3)

        backBtn3.setOnClickListener {
            // Finish the current activity to go back
            finish()
        }
    }
}