package com.example.myexamease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserProfileActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var backBtn4: Button
    private lateinit var logOutBtn: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        // Initialize EditTexts and Button
        nameEditText = findViewById(R.id.etb1)
        emailEditText = findViewById(R.id.etb2)
        backBtn4 = findViewById(R.id.backBtn4)
        logOutBtn = findViewById<Button>(R.id.logOutBtn)

        // Retrieve current user's data from Firebase database
        val currentUser = mAuth.currentUser
        currentUser?.let { user ->
            val userId = user.uid
            dbRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val name = dataSnapshot.child("name").getValue(String::class.java)
                        val email = dataSnapshot.child("email").getValue(String::class.java)
                        val password = dataSnapshot.child("password").getValue(String::class.java)

                        // Display user's data in EditTexts
                        nameEditText.setText(name)
                        emailEditText.setText(email)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                    Toast.makeText(
                        this@UserProfileActivity,
                        "Failed to retrieve user data: ${databaseError.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        // Set OnClickListener for back button
        backBtn4.setOnClickListener {
            onBackPressed() // Navigate back to previous activity
        }

        // Set OnClickListener for logout button
                logOutBtn.setOnClickListener {
                    mAuth.signOut() // Sign out the current user
                    // Redirect to the login page
                    val intent = Intent(this@UserProfileActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()  // Finish the current activity
                }
    }
}
