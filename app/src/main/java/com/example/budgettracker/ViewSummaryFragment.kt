package com.example.budgettracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ViewSummaryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_summary, container, false)

        val summaryTextView = view.findViewById<TextView>(R.id.summary_text_view)

        // Update summary
        val summary = ExpensesData.getSummary()
        summaryTextView.text = summary

        return view
    }
}
