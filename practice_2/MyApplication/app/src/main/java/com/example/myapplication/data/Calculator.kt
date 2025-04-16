package com.example.myapplication.data

import com.example.myapplication.OperationType

class Calculator {
    fun performOperation(firstNumber: Int, secondNumber: Int, operation: OperationType): Int {
        return when (operation) {
            OperationType.ADDITION -> firstNumber + secondNumber
            OperationType.SUBTRACTION -> firstNumber - secondNumber
            OperationType.MULTIPLICATION -> firstNumber * secondNumber
            OperationType.DIVISION -> if (secondNumber != 0) firstNumber / secondNumber else throw ArithmeticException("Division by zero")
            OperationType.NONE -> secondNumber
        }
    }
}
