package com.example.myexamease

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var nameEditText: TextInputEditText
    private lateinit var icNumberEditText: TextInputEditText
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var dobEditText: TextInputEditText
    private lateinit var ethnicityEditText: TextInputEditText
    private lateinit var religionEditText: TextInputEditText
    private lateinit var entryYearEditText: TextInputEditText
    private lateinit var addressEditText: TextInputEditText
    private lateinit var postcodeEditText: TextInputEditText
    private lateinit var cityEditText: TextInputEditText
    private lateinit var stateEditText: TextInputEditText
    private lateinit var phoneEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var subjectsEditText: TextInputEditText
    private lateinit var resetButton: Button
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText)
        icNumberEditText = findViewById(R.id.icNumberEditText)
        maleRadioButton = findViewById(R.id.maleRadioButton)
        femaleRadioButton = findViewById(R.id.femaleRadioButton)
        dobEditText = findViewById(R.id.dobEditText)
        ethnicityEditText = findViewById(R.id.ethnicityEditText)
        religionEditText = findViewById(R.id.religionEditText)
        entryYearEditText = findViewById(R.id.entryYearEditText)
        addressEditText = findViewById(R.id.addressEditText)
        postcodeEditText = findViewById(R.id.postcodeEditText)
        cityEditText = findViewById(R.id.cityEditText)
        stateEditText = findViewById(R.id.stateEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        emailEditText = findViewById(R.id.emailEditText)
        subjectsEditText = findViewById(R.id.subjectsEditText)
        resetButton = findViewById<Button>(R.id.resetButton)
        submitButton = findViewById(R.id.submitButton)

        // Set OnClickListener for reset button
        resetButton.setOnClickListener {
            // Clear all EditText fields
            nameEditText.text?.clear()
            icNumberEditText.text?.clear()
            dobEditText.text?.clear()
            ethnicityEditText.text?.clear()
            religionEditText.text?.clear()
            entryYearEditText.text?.clear()
            addressEditText.text?.clear()
            postcodeEditText.text?.clear()
            cityEditText.text?.clear()
            stateEditText.text?.clear()
            phoneEditText.text?.clear()
            emailEditText.text?.clear()
            subjectsEditText.text?.clear()
        }

        dobEditText.setOnClickListener {
            showDatePicker()
        }

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            registerUser()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                dobEditText.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun registerUser() {
        // Retrieve data from EditText fields
        val name = nameEditText.text.toString().trim()
        val icNumber = icNumberEditText.text.toString().trim()
        val dob = dobEditText.text.toString().trim()
        val gender = if (maleRadioButton.isChecked) "Male" else "Female"
        val ethnicity = ethnicityEditText.text.toString().trim()
        val religion = religionEditText.text.toString().trim()
        val entryYear = entryYearEditText.text.toString().trim()
        val address = addressEditText.text.toString().trim()
        val postcode = postcodeEditText.text.toString().trim()
        val city = cityEditText.text.toString().trim()
        val state = stateEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val subjects = subjectsEditText.text.toString().trim()

        // Perform validation
        if (name.isEmpty() || icNumber.isEmpty() || dob.isEmpty() || ethnicity.isEmpty() ||
            religion.isEmpty() || entryYear.isEmpty() || address.isEmpty() || postcode.isEmpty() ||
            city.isEmpty() || state.isEmpty() || phone.isEmpty() || email.isEmpty() || subjects.isEmpty()
        ) {
            // Show error message if any field is empty
            showToast("All fields are required")
            return
        }

        // Perform further validation as needed (e.g., validate email format, phone number format)

        // If all validation passes, you can proceed with the registration process
        // For demonstration purposes, we'll just show a success message
        showToast("User registered successfully")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

