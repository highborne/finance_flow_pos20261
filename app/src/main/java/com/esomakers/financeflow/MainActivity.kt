package com.esomakers.financeflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esomakers.financeflow.ui.theme.FinanceFlowTheme
import com.esomakers.financeflow.ui.theme.Spacing
import com.esomakers.financeflow.ui.transaction_add.TransactionAddScreen
import com.esomakers.financeflow.ui.transaction_list.TransactionListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FinanceFlowApp()
                }
            }
        }
    }
}

@Composable
fun FinanceFlowApp() {
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().statusBarsPadding()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(Spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(Spacing.extraLarge)
            )
            Text(
                modifier = Modifier.padding(Spacing.small),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge
            )
        }

        HorizontalDivider()

        Box(modifier = Modifier.fillMaxWidth().padding(Spacing.small).weight(1f)) {
            NavHost(
                navController = navController,
                startDestination = "transaction_list"
            ) {
                composable("transaction_list") {
                    TransactionListScreen(
                        onNavigateToAddTransaction = {
                            navController.navigate("transaction_add")
                        }
                    )
                }
                composable("transaction_add") {
                    TransactionAddScreen(
                        onNavigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FinanceFlowAppPreview() {
    FinanceFlowTheme {
        FinanceFlowApp()
    }
}