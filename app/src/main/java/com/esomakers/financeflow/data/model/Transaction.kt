package com.esomakers.financeflow.data.model

import com.esomakers.financeflow.R

enum class TransactionType {
    INCOME,
    EXPENSE
}

enum class TransactionCategory(
    val type: TransactionType,
    val labelRes: Int
) {
    SALARY(TransactionType.INCOME, R.string.category_salary),
    INVESTMENTS(TransactionType.INCOME, R.string.category_investments),
    FREELANCE(TransactionType.INCOME, R.string.category_freelance),
    OTHER_INCOME(TransactionType.INCOME, R.string.category_other_income),

    HOUSING(TransactionType.EXPENSE, R.string.category_housing),
    FOOD(TransactionType.EXPENSE, R.string.category_food),
    TRANSPORTATION(TransactionType.EXPENSE, R.string.category_transportation),
    HEALTH(TransactionType.EXPENSE, R.string.category_health),
    EDUCATION(TransactionType.EXPENSE, R.string.category_education),
    LEISURE(TransactionType.EXPENSE, R.string.category_leisure),
    CLOTHING(TransactionType.EXPENSE, R.string.category_clothing),
    SUBSCRIPTIONS(TransactionType.EXPENSE, R.string.category_subscriptions),
    DEPENDENTS_AND_PETS(TransactionType.EXPENSE, R.string.category_dependents_and_pets),
    FINANCIAL_EXPENSES(TransactionType.EXPENSE, R.string.category_financial_expenses);

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
