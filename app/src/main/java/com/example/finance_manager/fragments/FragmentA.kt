package com.example.finance_manager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.finance_manager.databinding.FragmentABinding

class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gdBtnCalculate.setOnClickListener {
            val salary = binding.gdEditSalary.text.toString().toDoubleOrNull() ?: 0.0
            val rent = binding.gdEditRent.text.toString().toDoubleOrNull() ?: 0.0
            val food = binding.gdEditFood.text.toString().toDoubleOrNull() ?: 0.0

            val bundle = bundleOf(
                KEY_SALARY to salary,
                KEY_RENT to rent,
                KEY_FOOD to food
            )
            parentFragmentManager.setFragmentResult(RESULT_KEY, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val RESULT_KEY = "gd_finance_result"
        const val KEY_SALARY = "gd_salary"
        const val KEY_RENT = "gd_rent"
        const val KEY_FOOD = "gd_food"
    }
}
