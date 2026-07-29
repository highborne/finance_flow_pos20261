package com.esomakers.financeflow.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esomakers.financeflow.ui.theme.BorderDark
import com.esomakers.financeflow.ui.theme.FinanceFlowTheme
import com.esomakers.financeflow.ui.theme.Spacing
import com.esomakers.financeflow.ui.theme.SurfaceDark
import com.esomakers.financeflow.ui.theme.TextMutedDark

@Composable
fun TransactionTypeCard(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    labelRes: Int,
    iconTint: Color,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(Spacing.medium),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceDark
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) iconTint else BorderDark
        )
    ) {
        Row(
            modifier = Modifier.padding(Spacing.large).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(Spacing.large)
            )

            Text(
                text = stringResource(id = labelRes).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = if (selected) iconTint else TextMutedDark,
                modifier = Modifier.padding(start = Spacing.extraSmall)
            )
        }
    }
}

@Preview
@Composable
private fun TransactionTypeSectionsPreview() {
    FinanceFlowTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.small)) {
            TransactionTypeCard(
                modifier = Modifier.weight(1f),
                iconRes = com.esomakers.financeflow.R.drawable.ic_revenue,
                labelRes = com.esomakers.financeflow.R.string.label_income,
                iconTint = com.esomakers.financeflow.ui.theme.IncomeGreen,
                selected = true
            )
            TransactionTypeCard(
                modifier = Modifier.weight(1f),
                iconRes = com.esomakers.financeflow.R.drawable.ic_expense,
                labelRes = com.esomakers.financeflow.R.string.label_expenses,
                iconTint = com.esomakers.financeflow.ui.theme.ExpenseRed,
                selected = false
            )
        }
    }
}
