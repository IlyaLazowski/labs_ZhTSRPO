package com.example.prog8

import android.media.audiofx.AudioEffect.Descriptor

data class Transaction(
    var id: Int,
    var userId: Int,
    var description: String,
    var type: String,
    var sum: String,
    var date: String  // Добавлено поле для даты
)