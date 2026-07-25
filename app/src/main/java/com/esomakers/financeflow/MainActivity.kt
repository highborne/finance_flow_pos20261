package com.esomakers.financeflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esomakers.financeflow.ui.theme.FinanceFlowTheme
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

    NavHost(
        navController = navController,
        startDestination = "transaction_list"
    ) {
        composable("transaction_list") {
            TransactionListScreen(
                onNavigateToAddTransaction = {
                    navController.navigate("add_transaction")
                }
            )
        }
        composable("add_transaction") {
            TransactionAddScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
