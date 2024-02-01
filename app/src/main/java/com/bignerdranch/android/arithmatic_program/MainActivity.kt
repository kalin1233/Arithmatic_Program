package com.bignerdranch.android.arithmatic_program

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.Button
import android.widget.EditText




class MainActivity : ComponentActivity() {

    private lateinit var calculatorEditText: EditText
    private lateinit var resultEditText: EditText

    private var operator: String = ""
    private var num1: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculatorEditText = findViewById(R.id.calculator)
        resultEditText = findViewById(R.id.result)
    }

    fun onDigit(view: View) {
        calculatorEditText.append((view as Button).text)
    }

    fun onOperator(view: View) {
        operator = (view as Button).text.toString()
        num1 = calculatorEditText.text.toString().toDouble()
        calculatorEditText.text.clear()
    }

    fun onEqual(view: View) {
        try {
            val num2 = calculatorEditText.text.toString().toDouble()
            val result = calculateResult(num1, operator, num2)
            resultEditText.setText(result.toString())
        } catch (e: NumberFormatException) {
            resultEditText.setText("Invalid Input")
        } catch (e: ArithmeticException) {
            resultEditText.setText("Error: ${e.message}")
        }
    }

    fun onClear(view: View) {
        calculatorEditText.text.clear()
        resultEditText.text.clear()
        operator = ""
        num1 = 0.0
    }

    private fun calculateResult(num1: Double, operator: String, num2: Double): Double {
        return when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 != 0.0) {
                    num1 / num2
                } else {
                    throw ArithmeticException("Divide by Zero not allowed.")
                }
            }
            "sqrt" -> {
                if (num1 >= 0) {
                    Math.sqrt(num1)
                } else {
                    throw ArithmeticException("Square root of a negative number is not allowed.")
                }
            }
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }


    fun onDecimalPoint(view: View) {
        // Handle decimal point logic if needed
        calculatorEditText.append(".")
    }
}

