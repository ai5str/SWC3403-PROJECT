package com.example.myexamease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val nameEditText = findViewById<EditText>(R.id.name)
        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirm_password)
        val signupButton = findViewById<Button>(R.id.signupButton)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val loginText = findViewById<TextView>(R.id.loginText)

        signupButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Create user in Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Save user data to Firebase Database
                            val userId = mAuth.currentUser?.uid
                            userId?.let { uid ->
                                val userRef = database.getReference("Users").child(uid)
                                userRef.child("name").setValue(name)
                                userRef.child("email").setValue(email)

                                Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()

                                // Navigate to MainActivity2 (or LoginActivity) after successful signup
                                val intent = Intent(this, MainActivity2::class.java)
                                startActivity(intent)
                                finish() // Finish current activity to prevent back navigation
                            }
                        } else {
                            Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }

        resetButton.setOnClickListener {
            nameEditText.text.clear()
            emailEditText.text.clear()
            passwordEditText.text.clear()
            confirmPasswordEditText.text.clear()
        }

        loginText.setOnClickListener {
            // Navigate to MainActivity2 (or LoginActivity)
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}