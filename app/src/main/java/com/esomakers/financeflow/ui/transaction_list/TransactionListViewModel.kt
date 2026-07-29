package com.esomakers.financeflow.ui.transaction_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esomakers.financeflow.data.model.Transaction
import com.esomakers.financeflow.data.model.TransactionType
import com.esomakers.financeflow.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TransactionListUiState(
    val totalBalance: Double = 0.0,
    val totalExpanses: Double = 0.0,
    val totalIncome: Double = 0.0,
    val transactions: List<Transaction> = emptyList()
)

class TransactionListViewModel (
    private val repository: TransactionRepository = TransactionRepository()
): ViewModel() {
    private val _uiState = MutableStateFlow(TransactionListUiState())
    val uiState: StateFlow<TransactionListUiState> = _uiState.asStateFlow()

    init {
        observeTransactions()
    }

    private fun observeTransactions() {
        viewModelScope.launch{
            repository.getTransactionsStream().collect { transactions ->
                val income = transactions
                    .filter { it.type == TransactionType.INCOME }
                    .sumOf {it.amount}

                val expense = transactions
                    .filter { it.type == TransactionType.EXPENSE }
                    .sumOf { it.amount }

                _uiState.update { currentState ->
                    currentState.copy(
                        transactions = transactions,
                        totalIncome = income,
                        totalExpanses = expense,
                        totalBalance = income - expense
                    )
                }
            }
        }
    }
}
