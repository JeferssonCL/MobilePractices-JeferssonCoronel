package com.example.componentes.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaginationViewModel : ViewModel() {

    private val _totalPages = 5
    private val _currentPage = MutableLiveData<Int>().apply { value = 1 }
    val currentPage: LiveData<Int> = _currentPage

    private val _pageContent = MutableLiveData<String>()
    val pageContent: LiveData<String> = _pageContent

    init {
        loadPageContent(1)
    }

    fun navigateToPage(pageNumber: Int) {
        if (pageNumber in 1.._totalPages) {
            _currentPage.value = pageNumber
            loadPageContent(pageNumber)
        }
    }

    private fun loadPageContent(pageNumber: Int) {
        _pageContent.value = "Contenido de la página $pageNumber"
    }
}