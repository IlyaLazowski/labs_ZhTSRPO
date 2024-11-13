package com.example.prog8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(
    private val transactions: List<Transaction>,
    private val onItemLongClick: (Transaction) -> Unit // Обработчик долгого нажатия
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        // Инфляция разметки элемента списка
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        // Привязка данных к элементу
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount() = transactions.size // Возвращает количество элементов в списке

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        private val sumTextView: TextView = itemView.findViewById(R.id.sumTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val transactionImageView: ImageView = itemView.findViewById(R.id.transactionImageView)

        fun bind(transaction: Transaction) {
            // Заполнение данных
            typeTextView.text ="Тип: "+ transaction.type
            dateTextView.text ="Дата: "+  transaction.date

            if (transaction.type=="Зарплата"||transaction.type=="Инвестиции"||transaction.type=="Продажи"||transaction.type=="Награды"||transaction.type=="Частичная занятость"||transaction.type=="Другие доходы"){
                sumTextView.text ="Сумма: + "+  transaction.sum
            }else  sumTextView.text ="Сумма: - "+  transaction.sum
           // dateTextView.text ="Дата: "+  transaction.date
            if (transaction.type == "Зарплата") transactionImageView.setImageResource(R.drawable.credit_card_icon) // Замените на нужное изображение
            if (transaction.type == "Инвестиции") transactionImageView.setImageResource(R.drawable.investition_icon)
            if (transaction.type == "Продажи") transactionImageView.setImageResource(R.drawable.sale_icon)
            if (transaction.type == "Награды") transactionImageView.setImageResource(R.drawable.gift_icon)
            if (transaction.type == "Частичная занятость") transactionImageView.setImageResource(R.drawable.timer_icon)
            if (transaction.type == "Другие доходы") transactionImageView.setImageResource(R.drawable.dollar_icone)
            if (transaction.type == "Обучение") transactionImageView.setImageResource(R.drawable.book)
            if (transaction.type == "Еда") transactionImageView.setImageResource(R.drawable.food)
            if (transaction.type == "Спорт") transactionImageView.setImageResource(R.drawable.sport)
            if (transaction.type == "Развлечения") transactionImageView.setImageResource(R.drawable.party)
            if (transaction.type == "Транспорт") transactionImageView.setImageResource(R.drawable.train)
            if (transaction.type == "Другие траты") transactionImageView.setImageResource(R.drawable.minus)



            // Устанавливаем обработчик долгого нажатия
            itemView.setOnLongClickListener {
                onItemLongClick(transaction) // Вызываем обработчик с текущей транзакцией
                true // Возвращаем true, чтобы указать, что событие обработано
            }
        }
    }
}