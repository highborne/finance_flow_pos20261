package com.esomakers.financeflow.ui.transaction_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esomakers.financeflow.R
import com.esomakers.financeflow.ui.components.Cards
import com.esomakers.financeflow.ui.components.TransactionItem
import com.esomakers.financeflow.ui.theme.FinanceFlowTheme
import com.esomakers.financeflow.ui.theme.Spacing
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TransactionListScreen(
    onNavigateToAddTransaction: () -> Unit,
    viewModel: TransactionListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddTransaction,
                containerColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.title_new_transaction))
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                Cards(
                    title = stringResource(id = R.string.label_total_balance),
                    iconRes = R.drawable.ic_balance,
                    value = currencyFormat.format(uiState.totalBalance),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.small)
                ) {
                    Cards(
                        modifier = Modifier.weight(1f),
                        title = stringResource(id = R.string.label_income),
                        iconRes = R.drawable.ic_revenue,
                        value = currencyFormat.format(uiState.totalIncome),
                        valueStyle = MaterialTheme.typography.bodyMedium
                    )

                    Cards(
                        modifier = Modifier.weight(1f),
                        title = stringResource(id = R.string.label_expenses),
                        iconRes = R.drawable.ic_expense,
                        value = currencyFormat.format(uiState.totalExpanses),
                        valueStyle = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Column(modifier = Modifier.fillMaxSize().padding(vertical = Spacing.small)) {
                Text(
                    text = stringResource(id = R.string.section_recent_transactions).uppercase()
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(Spacing.small),
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(Spacing.small)
                ) {
                    items(uiState.transactions) { transaction ->
                        TransactionItem(transaction = transaction)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TransactionListScreenPreview() {
    FinanceFlowTheme {
        TransactionListScreen(onNavigateToAddTransaction = {}, viewModel())
    }
}
