package com.example.tipcaculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.floor
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private var cost: Double = 0.0
    private var stringPercent: String = ""
    private var intPercent = 0
    private var buffer: String = ""
    private lateinit var btnCaculate: Button
    private lateinit var etMoney: EditText
    private lateinit var etPercent: RadioGroup
    private lateinit var tvResult: TextView
    private lateinit var switchRoundUp: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCaculate = findViewById<Button>(R.id.btnCalculate)
        etMoney = findViewById<EditText>(R.id.etInputCost)
        etPercent = findViewById<RadioGroup>(R.id.rgOption)
        tvResult = findViewById<TextView>(R.id.tvResult)
        switchRoundUp = findViewById<Switch>(R.id.swRoundTip)
        btnCaculate.setOnClickListener {
            buffer = etMoney.text.toString()
            if (buffer.isNotEmpty()) {
                cost = etMoney.text.toString().toDouble()
                intPercent = getPercent()
                var result: Double = cost * intPercent / 100
                checkSwitchRoundUp(result)
            } else {
                tvResult.text = "0"
            }
        }
    }

    private fun checkSwitchRoundUp(result: Double): Unit {
        if (switchRoundUp.isChecked) {
            tvResult.text = result.roundToInt().toString()
        } else {
            tvResult.text = floor(result).toInt().toString()
        }
    }

    private fun getPercent(): Int {
        stringPercent = findViewById<RadioButton>(etPercent.checkedRadioButtonId).text.toString()
        for (i in stringPercent.indices) {
            if (stringPercent[i] == '%') {
                return Integer.parseInt(stringPercent.substring(i - 2, i))
            }
        }
        return 0
    }
}