package com.esomakers.financeflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.esomakers.financeflow.R
import com.esomakers.financeflow.data.model.Transaction
import com.esomakers.financeflow.data.model.TransactionType
import com.esomakers.financeflow.ui.theme.ExpenseRed
import com.esomakers.financeflow.ui.theme.IncomeGreen
import com.esomakers.financeflow.ui.theme.Spacing
import com.esomakers.financeflow.ui.theme.SurfaceDark
import com.esomakers.financeflow.ui.theme.TextMutedDark
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
@Composable
fun TransactionItem(
    transaction: Transaction,
    modifier: Modifier = Modifier
) {
    val currencyFormat = NumberFormat.getCurrencyInstance()
    val dateFormat =  SimpleDateFormat(stringResource(id = R.string.date_format_pattern), Locale("pt", "BR")).format(Date(transaction.date))

    val isIncome = transaction.type == TransactionType.INCOME
    val valuePrefix = if (isIncome) "+ " else "- "
    val valueColor = if (isIncome) IncomeGreen else ExpenseRed
    val icon = if (isIncome) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward
    val iconBg = valueColor.copy(alpha = 0.1f)

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Spacing.medium),
        color = SurfaceDark
    ) {
        Row(
            modifier = Modifier.padding(Spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(Spacing.large)
                    .background(iconBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = valueColor,
                    modifier = Modifier.size(Spacing.medium)
                )
            }

            Spacer(modifier = Modifier.width(Spacing.medium))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.description,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = transaction.category.name.lowercase(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = valuePrefix + currencyFormat.format(transaction.amount),
                    color = valueColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dateFormat.format(Date(transaction.date)),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMutedDark
                )
            }
        }
    }
}
