package com.esomakers.financeflow.data.repository

import android.util.Log
import com.esomakers.financeflow.data.model.Transaction
import com.esomakers.financeflow.data.model.TransactionCategory
import com.esomakers.financeflow.data.model.TransactionType
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class TransactionRepository (
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val collectionRef = firestore.collection("transactions")

    suspend fun saveTransaction(transaction: Transaction): Result<Unit> {
        return try {
            val docRef = if (transaction.id.isEmpty()) {
                collectionRef.document()
            } else {
                collectionRef.document(transaction.id)
            }

            val finalTransaction = transaction.copy(id = docRef.id)

            docRef.set(finalTransaction).await()
            Log.d("TransactionRepository", "Transaction saved successfully: ${finalTransaction.id}")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("TransactionRepository", "Error saving transaction", e)
            Result.failure(e)
        }
    }

    //remove
    suspend fun createTestTransactions() {
        val testTransactions = listOf(
            Transaction(description = "Salário", amount = 5000.0, type = TransactionType.INCOME, category = TransactionCategory.SALARY),
            Transaction(description = "Freelance Design", amount = 1200.0, type = TransactionType.INCOME, category = TransactionCategory.FREELANCE),
            Transaction(description = "Venda de Items", amount = 300.0, type = TransactionType.INCOME, category = TransactionCategory.OTHER_INCOME),
            Transaction(description = "Aluguel", amount = 1500.0, type = TransactionType.EXPENSE, category = TransactionCategory.HOUSING),
            Transaction(description = "Supermercado", amount = 600.0, type = TransactionType.EXPENSE, category = TransactionCategory.FOOD),
            Transaction(description = "Assinatura Netflix", amount = 55.90, type = TransactionType.EXPENSE, category = TransactionCategory.SUBSCRIPTIONS)
        )

        testTransactions.forEach { saveTransaction(it) }
    }

    fun getTransactionsStream(): Flow<List<Transaction>> = callbackFlow {
        val listener = collectionRef
            .orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val transactions = snapshot.toObjects(Transaction::class.java)
                    trySend(transactions)

                    Log.d("TransactionRepository", "Transactions updated: ${transactions} items")
                }
            }
        awaitClose { listener.remove() }
    }

    suspend fun deleteTransaction(transactionId: String): Result<Unit> {
        return try {
            collectionRef.document(transactionId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}