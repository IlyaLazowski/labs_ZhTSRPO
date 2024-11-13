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

class RedTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_red_transaction)
        val transactionId = intent.getIntExtra("TRANSACTION_ID", -1)
        val userId = intent.getIntExtra("USER_ID", -1)

        val db = DbHelper(this, null)
        val transaction = db.getTransactionById(transactionId)
        val transactionDescription: EditText = findViewById(R.id.trans_description_red)
        val transactionSum: EditText = findViewById(R.id.trans_sum_red)
        transactionDescription.setText(transaction?.description ?: "Нет описания")
        transactionSum.setText(transaction?.sum ?: "Нет суммы")
        val linkToBack: TextView = findViewById(R.id.link_to_back)
        val button: Button = findViewById(R.id.button_add_red)

        val button_del:Button = findViewById(R.id.button_del_tran)

        button_del.setOnClickListener {
            db.deleteTransactionById(transactionId)
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USER_ID",userId )
            startActivity(intent)
        }

        linkToBack.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USER_ID",userId )
            startActivity(intent)
        }

        button.setOnClickListener {
            val description = transactionDescription.text.toString().trim()
            val sum = transactionSum.text.toString().trim()
            transaction?.description=description
            transaction?.sum=sum
            db.updateTransaction(transaction)
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USER_ID",userId )
            startActivity(intent)
            finish()
        }
    }





    }
