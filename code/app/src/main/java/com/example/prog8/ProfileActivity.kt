package com.example.prog8

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Получение ID пользователя из Intent
        val userId = intent.getIntExtra("USER_ID", -1) // -1 - значение по умолчанию, если ID не передан
        val db = DbHelper(this, null)
        val user = db.getById(userId)

        val userLogin: TextView  = findViewById(R.id.text_name_real)
        val userEmail: TextView  = findViewById(R.id.text_email_real)
        val id: TextView = findViewById(R.id.text_id)

        id.text = "ID: " + user?.id.toString()
        userEmail.text = user?.email.toString()
        userLogin.text = user?.login.toString()
        println(userLogin.toString())



        val exitImageView: ImageView = findViewById(R.id.image_exit_from_accaunt)
        val toMinus: ImageView = findViewById(R.id.image_shopping_cart)
        val toPlus: ImageView = findViewById(R.id.image_bag)
        val toRecord: ImageView = findViewById(R.id.image_records)


        toRecord.setOnClickListener {


            val intent = Intent(this, RecordsActivity::class.java)
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


        toMinus.setOnClickListener {


            val intent = Intent(this, MinusActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)

            //Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }


        // Устанавливаем обработчик нажатий
        exitImageView.setOnClickListener {


            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }


        fun getTotalAmountByUserId(userId: Int): Int {
            val db = DbHelper(this, null)
            val transactions = db.getTransactionsByUserId(userId) // Предполагается, что этот метод возвращает List<Transaction>

            var totalAmount = 0
            for (transaction in transactions) {
                if (transaction.type == "Зарплата"||transaction.type == "Инвестиции"||transaction.type == "Продажи"||transaction.type == "Награды"||transaction.type == "Частичная\nзанятость"||transaction.type == "Другие\nдоходы"){
                    totalAmount += transaction.sum.toInt()
                }else  totalAmount -= transaction.sum.toInt()
               // totalAmount += transaction.sum.toInt() // Преобразуем sum в Int
            }

            return totalAmount
        }

        var sum:TextView=findViewById(R.id.text_full_sum)
        sum.text = "Sum: " + getTotalAmountByUserId(userId).toString()




    }
}