package com.example.finance_manager.fragments

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

        val expenses = manager.totalExpenses
        val savings = manager.savingsAmount
        val remaining = manager.remaining
        val free = (remaining - savings).coerceAtLeast(0.0)
        val isDeficit = manager.isDeficit

        binding.gdTvTitle.text = getString(R.string.gd_title_result)
        binding.gdTvRemainingBig.text = "%.2f ₾".format(remaining)
        binding.gdTvTotalExpenses.text = "%.0f ₾".format(expenses)
        binding.gdTvSavings.text = "%.0f ₾".format(savings)
        binding.gdTvFree.text = "%.0f ₾".format(free)
        binding.gdTvStatus.text = if (isDeficit) "⚠ DEFICIT" else "✓ HEALTHY"
        binding.gdTvIdentity.text = "$FIRST_NAME $LAST_NAME  •  $BIRTH_YEAR"

        val ctx = requireContext()
        val textColor = ContextCompat.getColor(ctx, if (isDeficit) R.color.red else R.color.green)
        val headerBg = ContextCompat.getColor(ctx, if (isDeficit) R.color.gd_red_bg else R.color.gd_green_bg)

        binding.gdCardHeader.setCardBackgroundColor(headerBg)
        binding.gdTvTitle.setTextColor(textColor)
        binding.gdTvStatus.setTextColor(textColor)
        binding.gdTvRemainingBig.setTextColor(textColor)

        val pillBg = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 64f
            setStroke(2, textColor)
            setColor(Color.TRANSPARENT)
        }
        binding.gdTvStatus.background = pillBg

        setBarWeights(expenses, savings, free)

        binding.gdBtnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setBarWeights(expenses: Double, savings: Double, free: Double) {
        val total = (expenses + savings + free).coerceAtLeast(0.0001)
        setWeight(binding.gdBarExpenses, (expenses / total).toFloat())
        setWeight(binding.gdBarSavings, (savings / total).toFloat())
        setWeight(binding.gdBarRemaining, (free / total).toFloat())
    }

    private fun setWeight(v: View, weight: Float) {
        val lp = v.layoutParams as LinearLayout.LayoutParams
        lp.weight = weight.coerceAtLeast(0f)
        v.layoutParams = lp
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
