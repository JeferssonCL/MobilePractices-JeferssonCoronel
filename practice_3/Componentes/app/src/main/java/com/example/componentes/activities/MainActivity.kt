package com.example.componentes.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.componentes.R
import com.example.componentes.components.PaginationViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PaginationViewModel
    private lateinit var contentTextView: TextView
    private lateinit var pageButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[PaginationViewModel::class.java]

        contentTextView = findViewById(R.id.contentTextView)

        pageButtons = listOf(
            findViewById(R.id.btnPage1),
            findViewById(R.id.btnPage2),
            findViewById(R.id.btnPage3),
            findViewById(R.id.btnPage4),
            findViewById(R.id.btnPage5)
        )

        setupPaginationButtons()

        observeViewModel()
    }

    private fun setupPaginationButtons() {
        pageButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                viewModel.navigateToPage(index + 1)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.currentPage.observe(this) { currentPage ->
            updateButtonStyles(currentPage)
        }

        viewModel.pageContent.observe(this) { content ->
            contentTextView.text = content
        }
    }

    private fun updateButtonStyles(currentPage: Int) {
        pageButtons.forEachIndexed { index, button ->
            val pageNumber = index + 1

            if (pageNumber == currentPage) {
                button.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorPrimary)
                button.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            } else {
                button.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorInactive)
                button.setTextColor(ContextCompat.getColor(this, android.R.color.black))
            }
        }
    }
}