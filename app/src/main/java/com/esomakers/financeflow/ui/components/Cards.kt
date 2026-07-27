package com.esomakers.financeflow.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esomakers.financeflow.R
import com.esomakers.financeflow.ui.theme.BorderDark
import com.esomakers.financeflow.ui.theme.FinanceFlowTheme
import com.esomakers.financeflow.ui.theme.Spacing
import com.esomakers.financeflow.ui.theme.SurfaceDark
import com.esomakers.financeflow.ui.theme.TextMutedDark

@Composable
fun Cards(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int? = null,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    valueStyle: TextStyle = MaterialTheme.typography.headlineLarge
) {
    Card (
        modifier = modifier,
        shape = RoundedCornerShape(Spacing.medium),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        border = BorderStroke(1.dp, BorderDark)
    ) {
        Column(
            modifier = Modifier.padding(Spacing.medium)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                if(iconRes != null){
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(Spacing.large)
                    )
                }

                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = TextMutedDark,
                    modifier = Modifier.padding(Spacing.extraSmall)
                )
            }

            Spacer(modifier = Modifier.height(Spacing.small))

            Text(
                text = value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = valueStyle,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun CardsPreview() {
    FinanceFlowTheme {
        Cards(
            title = "Entradas",
            value = "R$ 999000",
            modifier = Modifier,
            iconRes = R.drawable.ic_revenue,
            iconTint = MaterialTheme.colorScheme.onSurface,
            valueStyle =  MaterialTheme.typography.headlineLarge
        )
    }
}