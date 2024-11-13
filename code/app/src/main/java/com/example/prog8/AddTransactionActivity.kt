package com.example.prog8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_transaction)

        val userId = intent.getIntExtra("USER_ID", -1) // -1 - значение по умолчанию
        val transactionType = intent.getStringExtra("USER_NAME") ?: "Неизвестный тип"  // Обработка null

        val db = DbHelper(this, null)
        val transactionDescription: EditText = findViewById(R.id.trans_description)
        val trans_sum: EditText = findViewById(R.id.trans_sum)
        val button: Button = findViewById(R.id.button_add)
        val linkToBack: TextView = findViewById(R.id.link_to_back)

        linkToBack.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        button.setOnClickListener {
            val description = transactionDescription.text.toString().trim()
            val sum = trans_sum.text.toString().trim()

            if (description.isEmpty() || sum.isEmpty()) {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            } else {
                // Получаем текущую дату
                val currentDate = getCurrentDate()

                val transaction = Transaction(0, userId, description, transactionType, sum, currentDate)
                db.addTransaction(transaction)  // Добавление транзакции в базу данных

                // Передача ID в ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)

                Toast.makeText(this, "Транзакция добавлена", Toast.LENGTH_SHORT).show()  // Успешное добавление

                // Очистка полей ввода
                transactionDescription.text.clear()
                trans_sum.text.clear()
            }
        }
    }

    // Метод для получения текущей даты в формате "YYYY-MM-DD"
    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())  // Возвращает текущую дату
    }
}