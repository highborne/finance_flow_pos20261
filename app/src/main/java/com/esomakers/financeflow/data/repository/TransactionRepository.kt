package com.esomakers.financeflow.data.repository

import com.esomakers.financeflow.data.model.Transaction
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

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
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
                }
            }
        awaitClose { listener.remove() }
    }
}