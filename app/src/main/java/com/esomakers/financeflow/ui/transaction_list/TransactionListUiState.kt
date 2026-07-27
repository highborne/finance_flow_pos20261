package com.esomakers.financeflow.ui.transaction_list

import com.esomakers.financeflow.data.model.Transaction

data class TransactionListUiState(
    val totalBalance: Double = 0.0,
    val totalExpanses: Double = 0.0,
    val totalIncome: Double = 0.0,
    val transactions: List<Transaction> = emptyList()
)
