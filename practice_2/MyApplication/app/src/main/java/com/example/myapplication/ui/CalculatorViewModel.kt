package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Calculator
import com.example.myapplication.OperationType

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()

    private val _displayText = MutableLiveData<String>().apply { value = "0" }
    val displayText: LiveData<String> = _displayText

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private var currentInput = ""
    private var previousNumber = 0
    private var currentOperation = OperationType.NONE

    private var memoryValue = 0

    fun onNumberPressed(number: Int) {
        currentInput += number
        updateDisplay()
    }

    fun onOperationPressed(operation: OperationType) {
        if (currentInput.isNotEmpty()) {
            previousNumber = currentInput.toInt()
            currentOperation = operation
            currentInput = ""
            updateDisplay()
        }
    }

    fun onEqualsPressed() {
        if (currentInput.isNotEmpty()) {
            try {
                val result = calculator.performOperation(previousNumber, currentInput.toInt(), currentOperation)
                currentInput = result.toString()
                currentOperation = OperationType.NONE
                updateDisplay()
            } catch (e: ArithmeticException) {
                _errorMessage.value = "Error: Division by zero"
                clearAll()
            }
        }
    }

    fun onClearOnePressed() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            updateDisplay()
        }
    }

    fun onClearAllPressed() {
        clearAll()
    }

    fun onMemoryClearPressed() {
        memoryValue = 0
        currentInput = ""
        updateDisplay()
    }

    fun onMemoryRecallPressed() {
        currentInput = memoryValue.toString()
        updateDisplay()
    }

    fun onMemoryAddPressed() {
        if (currentInput.isNotEmpty()) {
            try {
                memoryValue += currentInput.toInt()
                currentInput = ""
                updateDisplay()
            } catch (e: NumberFormatException) {
                _errorMessage.value = "Invalid number format"
            }
        }
    }

    fun onMemorySubtractPressed() {
        if (currentInput.isNotEmpty()) {
            try {
                memoryValue -= currentInput.toInt()
                currentInput = ""
                updateDisplay()
            } catch (e: NumberFormatException) {
                _errorMessage.value = "Invalid number format"
            }
        }
    }

    private fun clearAll() {
        currentInput = ""
        previousNumber = 0
        currentOperation = OperationType.NONE
        updateDisplay()
    }

    private fun updateDisplay() {
        _displayText.value = if (currentInput.isEmpty()) "0" else currentInput
    }
}