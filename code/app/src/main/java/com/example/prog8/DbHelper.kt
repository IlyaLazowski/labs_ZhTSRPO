package com.example.prog8

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 3) { // Увеличьте версию базы данных

    override fun onCreate(db: SQLiteDatabase) {
        Log.d("DbHelper", "Creating database and tables")

        val createUsersTable = """
            CREATE TABLE users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                login TEXT,
                email TEXT,
                pass TEXT
            )
        """.trimIndent()
        db.execSQL(createUsersTable)

        val createTransactionsTable = """
            CREATE TABLE transactions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                userId INTEGER,
                description TEXT,
                type TEXT,
                sum TEXT,
                date TEXT
            )
        """.trimIndent()
        db.execSQL(createTransactionsTable)

        Log.d("DbHelper", "Tables created")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d("DbHelper", "Upgrading database from version $oldVersion to $newVersion")
        db.execSQL("DROP TABLE IF EXISTS transactions")
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    // Метод для добавления пользователя
    fun addUser(user: User) {
        val values = ContentValues().apply {
            put("login", user.login)
            put("email", user.email)
            put("pass", user.pass)
        }

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    // Метод для добавления транзакции
    fun addTransaction(transaction: Transaction) {
        val values = ContentValues().apply {
            put("userId", transaction.userId)
            put("description", transaction.description)
            put("type", transaction.type)
            put("sum", transaction.sum)
            put("date", transaction.date)
        }

        val db = this.writableDatabase
        db.insert("transactions", null, values)
        db.close()
    }

    // Метод для получения транзакции по ID
    fun getTransactionById(transactionId: Int): Transaction? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM transactions WHERE id = ?", arrayOf(transactionId.toString()))

        return if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndex("userId"))
            val description = cursor.getString(cursor.getColumnIndex("description"))
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val sum = cursor.getString(cursor.getColumnIndex("sum"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            cursor.close()
            Transaction(transactionId, userId, description, type, sum, date)
        } else {
            cursor.close()
            null
        }
    }

    // Метод для получения всех транзакций
    fun getAllTransactions(): List<Transaction> {
        val transactions = mutableListOf<Transaction>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM transactions", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val userId = cursor.getInt(cursor.getColumnIndex("userId"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val sum = cursor.getString(cursor.getColumnIndex("sum"))
                val date = cursor.getString(cursor.getColumnIndex("date"))

                transactions.add(Transaction(id, userId, description, type, sum, date))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return transactions
    }

    // Метод для проверки пользователя
    fun getUser(email: String, pass: String): Boolean {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM users WHERE email = ? AND pass = ?", arrayOf(email, pass))
        val exists = result.moveToFirst()
        result.close()
        return exists
    }

    // Метод для получения транзакции по ID

    // Метод для получения пользователя по ID
    fun getById(userId: Int): User? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE id = ?", arrayOf(userId.toString()))

        return if (cursor.moveToFirst()) {
            val login = cursor.getString(cursor.getColumnIndex("login"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val pass = cursor.getString(cursor.getColumnIndex("pass"))
            cursor.close()
            User(userId, login, email, pass)
        } else {
            cursor.close()
            null
        }
    }

    // Метод для обновления транзакции
// Метод для обновления транзакции
    fun updateTransaction(transaction: Transaction?): Boolean {
        // Проверяем, что transaction не равен null
        if (transaction == null) {
            return false // Возвращаем false, если транзакция не существует
        }

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("userId", transaction.userId)
            put("description", transaction.description)
            put("type", transaction.type)
            put("sum", transaction.sum)
            put("date", transaction.date)
        }

        // Обновляем запись в таблице transactions по ID
        val rowsAffected = db.update("transactions", values, "id = ?", arrayOf(transaction.id.toString()))
        db.close()

        // Возвращаем true, если обновление произошло успешно
        return rowsAffected > 0
    }


    // Метод для получения ID пользователя по email
    fun getUserId(email: String): Int? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT id FROM users WHERE email = ?", arrayOf(email))

        return if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndex("id"))
            cursor.close()
            userId
        } else {
            cursor.close()
            null
        }
    }

    // Метод для удаления транзакции по ID
    fun deleteTransactionById(transactionId: Int): Boolean {
        val db = this.writableDatabase
        val rowsAffected = db.delete("transactions", "id = ?", arrayOf(transactionId.toString()))
        db.close()

        // Возвращаем true, если удаление произошло успешно
        return rowsAffected > 0
    }

    // Метод для очистки базы данных
    fun clearDatabase() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS transactions")
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    // Метод для получения транзакций по userId
    fun getTransactionsByUserId(userId: Int): List<Transaction> {
        val transactions = mutableListOf<Transaction>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM transactions WHERE userId = ?", arrayOf(userId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val sum = cursor.getString(cursor.getColumnIndex("sum"))
                val date = cursor.getString(cursor.getColumnIndex("date"))

                transactions.add(Transaction(id, userId, description, type, sum, date))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return transactions
    }
}