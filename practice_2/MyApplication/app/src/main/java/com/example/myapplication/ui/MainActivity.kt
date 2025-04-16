package com.example.myapplication.ui


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.OperationType

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var lblResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        lblResult = findViewById(R.id.lblResult)
        setupButtonListeners()
        observeViewModel()
    }

    private fun setupButtonListeners() {
        findViewById<Button>(R.id.btn0).setOnClickListener { viewModel.onNumberPressed(0) }
        findViewById<Button>(R.id.btn1).setOnClickListener { viewModel.onNumberPressed(1) }
        findViewById<Button>(R.id.btn2).setOnClickListener { viewModel.onNumberPressed(2) }
        findViewById<Button>(R.id.btn3).setOnClickListener { viewModel.onNumberPressed(3) }
        findViewById<Button>(R.id.btn4).setOnClickListener { viewModel.onNumberPressed(4) }
        findViewById<Button>(R.id.btn5).setOnClickListener { viewModel.onNumberPressed(5) }
        findViewById<Button>(R.id.btn6).setOnClickListener { viewModel.onNumberPressed(6) }
        findViewById<Button>(R.id.btn7).setOnClickListener { viewModel.onNumberPressed(7) }
        findViewById<Button>(R.id.btn8).setOnClickListener { viewModel.onNumberPressed(8) }
        findViewById<Button>(R.id.btn9).setOnClickListener { viewModel.onNumberPressed(9) }

        findViewById<Button>(R.id.btnPlus).setOnClickListener {
            viewModel.onOperationPressed(OperationType.ADDITION)
        }
        findViewById<Button>(R.id.btnMinus).setOnClickListener {
            viewModel.onOperationPressed(OperationType.SUBTRACTION)
        }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener {
            viewModel.onOperationPressed(OperationType.MULTIPLICATION)
        }
        findViewById<Button>(R.id.btnDivide).setOnClickListener {
            viewModel.onOperationPressed(OperationType.DIVISION)
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { viewModel.onEqualsPressed() }
        findViewById<Button>(R.id.btnClearone).setOnClickListener { viewModel.onClearOnePressed() }
        findViewById<Button>(R.id.btnClearEverything).setOnClickListener { viewModel.onClearAllPressed() }

        findViewById<Button>(R.id.btnMC).setOnClickListener {
            viewModel.onMemoryClearPressed()
        }
        findViewById<Button>(R.id.btnMR).setOnClickListener {
            viewModel.onMemoryRecallPressed()
        }
        findViewById<Button>(R.id.btnMPlus).setOnClickListener {
            viewModel.onMemoryAddPressed()
        }
        findViewById<Button>(R.id.btnMMinor).setOnClickListener {
            viewModel.onMemorySubtractPressed()
        }
    }

    private fun observeViewModel() {
        viewModel.displayText.observe(this) { displayText ->
            lblResult.text = displayText
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}