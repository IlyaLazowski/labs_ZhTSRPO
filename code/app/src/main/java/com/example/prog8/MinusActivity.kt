package com.example.prog8

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MinusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_minus)
        val userId = intent.getIntExtra("USER_ID", -1) // -1 - значение по умолчанию, если ID не передан
        val db = DbHelper(this, null)
        val user = db.getById(userId)



        val toPlus: ImageView = findViewById(R.id.imageView28)
        val toProfile: ImageView = findViewById(R.id.imageView390)
        val toRecord: ImageView = findViewById(R.id.imageView23)


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


        toPlus.setOnClickListener {


            val intent = Intent(this, PlusActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }



        val book: ImageView = findViewById(R.id.imageView29)

        book.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Обучение")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }


        val food: ImageView = findViewById(R.id.imageView30)

        food.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Еда")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        val sport: ImageView = findViewById(R.id.imageView31)

        sport.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Спорт")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        val party: ImageView = findViewById(R.id.imageView32)

        party.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Развлечения")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        val train: ImageView = findViewById(R.id.imageView33)

        train.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Транспорт")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        val other: ImageView = findViewById(R.id.imageView34)

        other.setOnClickListener {


            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra("USER_ID", userId)
            intent.putExtra("USER_NAME", "Другие\nтраты")
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }





    }
}