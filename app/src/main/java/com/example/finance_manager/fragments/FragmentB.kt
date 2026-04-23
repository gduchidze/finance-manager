package com.example.finance_manager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.finance_manager.R
import com.example.finance_manager.databinding.FragmentBBinding
import com.example.finance_manager.logic.FinanceManager
import com.example.finance_manager.model.FinanceModel

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()
        val model = FinanceModel(
            salary = args.getDouble(FragmentA.KEY_SALARY),
            rent = args.getDouble(FragmentA.KEY_RENT),
            food = args.getDouble(FragmentA.KEY_FOOD)
        )
        val manager = FinanceManager(model)

        binding.gdTvTitle.text = "Finance Report"
        binding.gdTvTotalExpenses.text = "Total expenses: %.2f".format(manager.totalExpenses)
        binding.gdTvSavings.text = "Savings (${FinanceManager.SAVINGS_PERCENT}%): %.2f".format(manager.savingsAmount)
        binding.gdTvRemaining.text = "Remaining: %.2f".format(manager.remaining)
        binding.gdTvStatus.text = if (manager.isDeficit) "Status: DEFICIT" else "Status: OK"
        binding.gdTvIdentity.text = "$FIRST_NAME $LAST_NAME — $BIRTH_YEAR"

        val colorRes = if (manager.isDeficit) R.color.red else R.color.green
        val color = ContextCompat.getColor(requireContext(), colorRes)
        listOf(
            binding.gdTvTitle,
            binding.gdTvTotalExpenses,
            binding.gdTvSavings,
            binding.gdTvRemaining,
            binding.gdTvStatus
        ).forEach { it.setTextColor(color) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FIRST_NAME = "Giorgi"
        const val LAST_NAME = "Duchidze"
        const val BIRTH_YEAR = 2004

        fun newInstance(bundle: Bundle): FragmentB = FragmentB().apply { arguments = bundle }
    }
}
