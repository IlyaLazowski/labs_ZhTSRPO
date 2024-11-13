package com.example.prog8

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_plus)
        val userId = intent.getIntExtra("USER_ID", -1) // -1 - значение по умолчанию, если ID не передан
        val db = DbHelper(this, null)
        val user = db.getById(userId)

        val toMinus: ImageView = findViewById(R.id.imageView)
        val toProfile: ImageView = findViewById(R.id.imageView3)
        val toRecord: ImageView = findViewById(R.id.imageView2)


        toRecord.setOnClickListener {


            val intent = Intent(this, RecordsActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        toProfile.setOnClickListener {


            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }


        toMinus.setOnClickListener {


            val intent = Intent(this, MinusActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }



        val salary: ImageView  = findViewById(R.id.imageView4)

        salary.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Зарплата")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }


        val investition: ImageView  = findViewById(R.id.imageView6)

        investition.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Инвестиции")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        val sale: ImageView  = findViewById(R.id.imageView7)

        sale.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Продажи")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        val gift: ImageView  = findViewById(R.id.imageView8)

        gift.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Награды")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        val timer: ImageView  = findViewById(R.id.imageView909)

        timer.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Частичная занятость")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        val other: ImageView  = findViewById(R.id.imageView10)

        other.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Другие\nдоходы")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }




    }
}