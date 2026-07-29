package com.esomakers.financeflow.ui.transaction_add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esomakers.financeflow.R
import com.esomakers.financeflow.data.model.TransactionCategory
import com.esomakers.financeflow.data.model.TransactionType
import com.esomakers.financeflow.ui.components.TransactionTypeCard
import com.esomakers.financeflow.ui.theme.ExpenseRed
import com.esomakers.financeflow.ui.theme.IncomeGreen
import com.esomakers.financeflow.ui.theme.Spacing
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionAddScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionAddViewModel = viewModel(),
) {
    val focusRequester = remember { FocusRequester() }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = viewModel.transactionDate
    )

    val isValueError = viewModel.transactionValue <= "0.0"
    val isDescriptionError = viewModel.transactionDescription.isEmpty()

    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = TransactionCategory.getByType(viewModel.transactionType)

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        viewModel.onTransactionDateChange(it)
                    }
                    showDatePicker = false
                }) {
                    Text(text = stringResource(id = android.R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(text = stringResource(id = android.R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background
    ) { innerPadding: PaddingValues ->
        Column(modifier = modifier.padding(innerPadding)) {
            OutlinedTextField(
                value = viewModel.transactionValue,
                onValueChange = { viewModel.onTransactionValueChange(it) },
                label = {
                    Text(text = stringResource(id = R.string.label_amount).uppercase())
                },
                isError = isValueError,
                supportingText = {
                    if(isValueError){
                        Text(text = stringResource(id = R.string.transaction_amount_error_message))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                enabled = !viewModel.isLoading
            )

            OutlinedTextField(
                value = viewModel.transactionDescription,
                onValueChange = { viewModel.onTransactionDescriptionChange(it) },
                label = {
                    Text(text = stringResource(id = R.string.label_description).uppercase())
                },
                isError = isDescriptionError,
                supportingText = {
                    if(isDescriptionError){
                        Text(text = stringResource(id = R.string.transaction_description_error_message))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                enabled = !viewModel.isLoading
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = !viewModel.isLoading) { showDatePicker = true }
            ) {
                OutlinedTextField(
                    value = viewModel.formattedDate,
                    onValueChange = { },
                    label = {
                        Text(text = stringResource(id = R.string.label_date).uppercase())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.small),
                    readOnly = true,
                    enabled = false,
                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                        disabledTextColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                        disabledLabelColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledBorderColor = androidx.compose.material3.MaterialTheme.colorScheme.outline,
                        disabledPlaceholderColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.small)) {
                Text(
                    text = stringResource(id = R.string.section_transaction_type).uppercase()
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.small),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.small)
                ) {
                    TransactionTypeCard(
                        modifier = Modifier.weight(1f),
                        iconRes = R.drawable.ic_revenue,
                        labelRes = R.string.label_income,
                        iconTint = IncomeGreen,
                        selected = viewModel.transactionType == TransactionType.INCOME,
                        onClick = { if (!viewModel.isLoading) viewModel.onTransactionTypeChange(TransactionType.INCOME) }
                    )

                    TransactionTypeCard(
                        modifier = Modifier.weight(1f),
                        iconRes = R.drawable.ic_expense,
                        labelRes = R.string.label_expenses,
                        iconTint = ExpenseRed,
                        selected = viewModel.transactionType == TransactionType.EXPENSE,
                        onClick = { if (!viewModel.isLoading) viewModel.onTransactionTypeChange(TransactionType.EXPENSE) }
                    )
                }
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spacing.small)) {
                Text(
                    text = stringResource(id = R.string.label_recommended_category).uppercase(),
                    modifier = Modifier.padding(horizontal = Spacing.small)
                )

                ExposedDropdownMenuBox(
                    expanded = categoryExpanded && !viewModel.isLoading,
                    onExpandedChange = { if (!viewModel.isLoading) categoryExpanded = !categoryExpanded },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.small)
                ) {
                    OutlinedTextField(
                        value = stringResource(id = viewModel.transactionCategory.labelRes),
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                        enabled = !viewModel.isLoading
                    )

                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(text = stringResource(id = category.labelRes)) },
                                onClick = {
                                    viewModel.onTransactionCategoryChange(category)
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Button(
                onClick = { viewModel.onTransactionSave() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.small),
                enabled = !viewModel.isLoading
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(Spacing.large),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(text = stringResource(id = R.string.button_save_transaction).uppercase())
                }
            }
        }
    }
}
