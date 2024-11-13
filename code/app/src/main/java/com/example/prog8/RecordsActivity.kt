package com.example.prog8

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecordsActivity : AppCompatActivity() {
    private var userId: Int = -1 // Храним ID пользователя

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_records)

        // Получаем пользовательский ID из Intent
        userId = intent.getIntExtra("USER_ID", -1)
        val db = DbHelper(this, null)
        val userTransactions = db.getTransactionsByUserId(userId)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = TransactionAdapter(userTransactions) { transaction ->
            // Обработка долгого нажатия на элемент
            showTransactionDetails(transaction)
        }
    }

    private fun showTransactionDetails(transaction: Transaction) {
        // Создаем Intent для перехода на AddTransactionActivity
        val intent = Intent(this, RedTransactionActivity::class.java)
        intent.putExtra("TRANSACTION_ID", transaction.id) // Передаем ID выделенной транзакции
        intent.putExtra("USER_ID", userId) // Передаем имя пользователя
        startActivity(intent) // Запускаем новое активити

        // Закрываем текущее активити, если это необходимо
        finish()
    }
}