package com.esomakers.financeflow.data.model

enum class TransactionType {
    INCOME,
    EXPENSE
}

enum class TransactionCategory(
    val type: TransactionType
) {
    SALARY(TransactionType.INCOME),
    INVESTMENTS(TransactionType.INCOME),
    FREELANCE(TransactionType.INCOME),
    OTHER_INCOME(TransactionType.INCOME),

    HOUSING(TransactionType.EXPENSE),
    FOOD(TransactionType.EXPENSE),
    TRANSPORTATION(TransactionType.EXPENSE),
    HEALTH(TransactionType.EXPENSE),
    EDUCATION(TransactionType.EXPENSE),
    LEISURE(TransactionType.EXPENSE),
    CLOTHING(TransactionType.EXPENSE),
    SUBSCRIPTIONS(TransactionType.EXPENSE),
    DEPENDENTS_AND_PETS(TransactionType.EXPENSE),
    FINANCIAL_EXPENSES(TransactionType.EXPENSE);

    companion object {
        fun getByType(type: TransactionType): List<TransactionCategory> {
            return entries.filter { it.type == type }
        }
    }
}

data class Transaction(
    val id: String = "",
    val description: String = "",
    val amount: Double = 0.0,
    val date: Long = System.currentTimeMillis(),
    val type: TransactionType = TransactionType.EXPENSE,
    val category: TransactionCategory = TransactionCategory.FOOD
)
