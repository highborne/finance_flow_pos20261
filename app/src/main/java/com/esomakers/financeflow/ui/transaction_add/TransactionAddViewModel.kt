package com.esomakers.financeflow.ui.transaction_add

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esomakers.financeflow.R
import com.esomakers.financeflow.data.model.Transaction
import com.esomakers.financeflow.data.model.TransactionCategory
import com.esomakers.financeflow.data.model.TransactionType
import com.esomakers.financeflow.data.repository.TransactionRepository
import com.esomakers.financeflow.utils.UiText
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class TransactionAddViewModel (
    private val repository: TransactionRepository = TransactionRepository()
): ViewModel() {
    var transactionValue by mutableStateOf("")
        private set

    var transactionDescription by mutableStateOf("")
        private set

    var transactionDate by mutableLongStateOf(System.currentTimeMillis())
        private set
    var transactionType by mutableStateOf( TransactionType.EXPENSE)
        private set

    var transactionCategory by mutableStateOf(TransactionCategory.FOOD)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<UiText?>(null)
        private set

    var isSaveSuccess by mutableStateOf(false)
        private set

    fun onTransactionValueChange(newValue: String){
        transactionValue = newValue
    }

    fun onTransactionDescriptionChange(newDescription: String){
        transactionDescription = newDescription
    }

    fun onTransactionTypeChange(newType: TransactionType){
        transactionType = newType
        transactionCategory = TransactionCategory.getByType(newType).firstOrNull() ?: TransactionCategory.FOOD
    }

    fun onTransactionCategoryChange(newCategory: TransactionCategory){
        transactionCategory = newCategory
    }

    fun onTransactionDateChange(newDate: Long) {
        transactionDate = newDate
    }

    fun clearError() {
        errorMessage = null
    }

    fun onTransactionSave() {
        val amount = transactionValue.toDoubleOrNull() ?: 0.0

        if(amount <= 0.0){
            errorMessage = UiText.StringResource(resId = R.string.transaction_amount_error_message)
            return
        }

        if (transactionDescription.isBlank()){
            errorMessage = UiText.StringResource(resId = R.string.transaction_description_error_message)
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            val transaction = Transaction(
                description = transactionDescription,
                amount = amount,
                type = transactionType,
                category = transactionCategory,
                date = transactionDate
            )

            val result = repository.saveTransaction(transaction)

            isLoading = false
            if(result.isSuccess){
                isSaveSuccess = true
            } else {
                errorMessage = UiText.StringResource(
                    R.string.transaction_save_error_message,
                    result.exceptionOrNull()?.message ?: ""
                )
            }
        }
    }

    val formattedDate: String by derivedStateOf {
        DATE_FORMATTER.format(Instant.ofEpochMilli(transactionDate))
    }

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            .withZone(ZoneId.of("UTC"))
    }
}
