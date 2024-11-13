package com.example.prog8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prog8.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        val userEmail: EditText = findViewById(R.id.user_email_auth)
        val userPass: EditText = findViewById(R.id.user_pass_auth)
        val button: Button = findViewById(R.id.button_auth)
        val linkToReg: TextView = findViewById(R.id.link_to_reg)

        linkToReg.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        button.setOnClickListener() {
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if (email == "" || pass == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(email, pass)
                if (isAuth) {
                    val id = db.getUserId(email)
                    if (id != null) {
                        // Передача ID в ProfileActivity
                        val intent = Intent(this, ProfileActivity::class.java)
                        intent.putExtra("USER_ID", id)
                        startActivity(intent)
                    }
                    Toast.makeText(this, "Пользователь $email авторизован", Toast.LENGTH_LONG).show()
                    userEmail.text.clear()
                    userPass.text.clear()
                } else {
                    Toast.makeText(this, "Пользователь $email не авторизован", Toast.LENGTH_LONG).show()
                }
            }
        }


    }
}