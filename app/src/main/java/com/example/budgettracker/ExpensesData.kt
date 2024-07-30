package com.example.budgettracker

object ExpensesData {
    private val expenses = mutableListOf<Expense>()

    data class Expense(
        val amount: Double,
        val description: String,
        val category: String,
        val type: String
    )

    fun addExpense(amount: Double, description: String, category: String, type: String) {
        expenses.add(Expense(amount, description, category, type))
    }

    fun getSummary(): String {val categoryTotals = mutableMapOf<String, Double>()
        val typeTotals = mutableMapOf<String, Double>()
        var totalAmount = 0.0

        // Calculate totals for each category, type, and overall total
        for (expense in expenses) {
            totalAmount += expense.amount
            categoryTotals[expense.category] = (categoryTotals[expense.category] ?: 0.0) + expense.amount
            typeTotals[expense.type] = (typeTotals[expense.type] ?: 0.0) + expense.amount
        }

        // Build the summary string
        val summary = StringBuilder()
        summary.append("Total: $${"%.2f".format(totalAmount)}\n")

        // Append category-wise totals
        for ((category, amount) in categoryTotals) {
            summary.append("${category}: $${"%.2f".format(amount)}\n")
        }

        // Append type-wise totals
        for ((type, amount) in typeTotals) {
            summary.append("${type}: $${"%.2f".format(amount)}\n")
        }

        return summary.toString().trim()
    }
}
