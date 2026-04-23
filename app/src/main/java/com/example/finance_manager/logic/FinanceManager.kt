package com.example.finance_manager.logic

import com.example.finance_manager.model.FinanceModel

class FinanceManager(private val model: FinanceModel) {

    val totalExpenses: Double
        get() = model.rent + model.food

    val remaining: Double
        get() = model.salary - totalExpenses

    val savingsAmount: Double
        get() = model.salary * SAVINGS_PERCENT / 100.0

    val afterSavings: Double
        get() = remaining - savingsAmount

    val isDeficit: Boolean
        get() = model.salary < totalExpenses

    companion object {
        const val LAST_NAME_LETTERS = 8
        const val BIRTH_MONTH = 4
        const val SAVINGS_PERCENT = LAST_NAME_LETTERS + BIRTH_MONTH
    }
}
