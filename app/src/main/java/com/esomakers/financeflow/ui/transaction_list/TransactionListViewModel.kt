package com.esomakers.financeflow.ui.transaction_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esomakers.financeflow.data.model.TransactionType
import com.esomakers.financeflow.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
                //remove
//                if (transactions.isEmpty()) {
//                    repository.createTestTransactions()
//                }

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
