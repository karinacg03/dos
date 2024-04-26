package com.adso.androcalcu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adso.androcalcu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operand1 = ""
    private var operand2 = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val numberButtons = arrayOf(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,

            )


        val operationButtons = arrayOf(
            binding.buttonPlus,
            binding.buttonMinus,
            binding.buttonMultiply,
            binding.buttonDivide
        )


        for (button in numberButtons) {
            button.setOnClickListener {
                onNumberButtonClick(button.text.toString())
            }
        }


        for (button in operationButtons) {
            button.setOnClickListener {
                onOperationButtonClick(button.text.toString())
            }
        }


        binding.buttonEqual.setOnClickListener {
            onEqualsButtonClick()
        }


        binding.buttonClear.setOnClickListener {
            onClearButtonClick()
        }
    }


    private fun onNumberButtonClick(number: String) {
        if (operator.isEmpty()) {
            operand1 += number
            binding.textView.text = operand1
        } else {
            operand2 += number
            binding.textView.text = operand2
        }
    }


    private fun onOperationButtonClick(op: String) {
        if (operand1.isNotEmpty() && operand2.isEmpty()) {
            operator = op
        } else if (operand1.isNotEmpty() && operand2.isNotEmpty()) {
            val result = performOperation()
            operand1 = result
            operand2 = ""
            operator = op
            binding.textView.text = operand1
        }
    }


    private fun onEqualsButtonClick() {
        if (operand1.isNotEmpty() && operand2.isNotEmpty() && operator.isNotEmpty()) {
            val result = performOperation()
            binding.textView.text = result
            operand1 = result
            operand2 = ""
            operator = ""
        }
    }

    private fun onClearButtonClick() {
        operand1 = ""
        operand2 = ""
        operator = ""
        binding.textView.text = "0"
    }

    private fun performOperation(): String {
        val num1 = operand1.toDouble()
        val num2 = operand2.toDouble()
        return when (operator) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> if (num2 != 0.0) (num1 / num2).toString() else "Error"
            else -> ""
        }
    }
}
