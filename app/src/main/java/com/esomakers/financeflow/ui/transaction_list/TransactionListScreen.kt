package com.esomakers.financeflow.ui.transaction_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.esomakers.financeflow.FinanceFlowApp
import com.esomakers.financeflow.ui.theme.FinanceFlowTheme

@Composable
fun TransactionListScreen(onNavigateToAddTransaction: () -> Unit) {
    Text(text = "Transaction List Screen")
}

@Preview
@Composable
private fun TransactionListScreenPreview() {
    FinanceFlowTheme {
        TransactionListScreen(onNavigateToAddTransaction = {})
    }
}