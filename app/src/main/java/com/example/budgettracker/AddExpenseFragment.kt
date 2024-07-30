package com.example.budgettracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast


class AddExpenseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)

        val amountInput = view.findViewById<EditText>(R.id.amount_input)
        val descriptionInput = view.findViewById<EditText>(R.id.description_input)
        val categorySpinner = view.findViewById<Spinner>(R.id.category_spinner)
        val saveButton = view.findViewById<Button>(R.id.save_button)

        // Populate spinner with categories
        val categories = arrayOf("Food", "Transportation", "Entertainment", "Bills", "Others")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        categorySpinner.adapter = adapter

        saveButton.setOnClickListener {
            val amount = amountInput.text.toString().toDoubleOrNull()
            val description = descriptionInput.text.toString()
            val category = categorySpinner.selectedItem.toString()
            val expenseTypeRadioGroup  = view.findViewById<RadioGroup>(R.id.radioGroupExpenseType)

            if (amount != null && description.isNotEmpty()) {
                // Determine the expense type
                val selectedExpenseTypeId = expenseTypeRadioGroup.checkedRadioButtonId
                val expenseType = when (selectedExpenseTypeId) {
                    R.id.radioOneTime -> "One-Time"
                    R.id.radioMonthly -> "Monthly"
                    else -> "Unknown"
                }
                ExpensesData.addExpense(amount, description, category, expenseType)
                Toast.makeText(context, "Expense added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
