package com.example.finance_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finance_manager.databinding.ActivityMainBinding
import com.example.finance_manager.fragments.FragmentA
import com.example.finance_manager.fragments.FragmentB

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.gd_fragment_container, FragmentA())
                .commit()
        }

        supportFragmentManager.setFragmentResultListener(
            FragmentA.RESULT_KEY,
            this
        ) { _, bundle ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.gd_fragment_container, FragmentB.newInstance(bundle))
                .addToBackStack(null)
                .commit()
        }
    }
}
