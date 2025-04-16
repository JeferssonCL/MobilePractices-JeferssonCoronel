package com.example.prueba1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var login: Button
    private lateinit var userNameText: EditText
    private lateinit var passwordText: EditText
    private val usuarios = mapOf("admin" to "admin", "jeff" to "jeff123")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        login = findViewById(R.id.login)
        userNameText = findViewById(R.id.usernameText)
        passwordText = findViewById(R.id.passwordText)
        setUpEventListeners()
    }

    private fun validateUser(): Boolean {
        val username = userNameText.text.toString().trim()
        val password = passwordText.text.toString().trim()

        return if (usuarios[username] == password) {
            true
        } else {
            Toast.makeText(this, "username or password is incorrect", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun setUpEventListeners() {
        login.setOnClickListener {
            if (validateUser()) {
                val username = userNameText.text.toString().trim()
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
            }
        }
    }
}
