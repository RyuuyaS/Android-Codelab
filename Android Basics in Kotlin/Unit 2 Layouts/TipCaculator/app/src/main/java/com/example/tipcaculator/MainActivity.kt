package com.example.tipcaculator

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcaculator.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {
    private var cost: Double = 0.0
    private var intPercent = 0
    private var buffer: String = ""
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etInputCost.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
        binding.btnCalculate.setOnClickListener {
            buffer = binding.etInputCost.text.toString()
            if (buffer.isNotEmpty()) {
                cost = binding.etInputCost.text.toString().toDouble()
                intPercent = getPercent()
                var result: Double = cost * intPercent / 100
                if (binding.swRoundTip.isChecked) {
                    result = kotlin.math.ceil(result)
                }
                displayTip(result)
            } else {
                binding.tvTipAmount.text = getString(R.string.tip_amount, "0")
            }
        }
    }

    private fun displayTip(result: Double): Unit {
        val tip = NumberFormat.getCurrencyInstance().format(result)
        binding.tvTipAmount.text = getString(R.string.tip_amount, tip)
    }

    private fun getPercent(): Int {
        intPercent = binding.rgOption.checkedRadioButtonId
        return when (intPercent) {
            R.id.rbEighteenPercent -> 18
            R.id.rbFifteenPercent -> 15
            R.id.rbTwentyPercent -> 20
            else -> 0
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}