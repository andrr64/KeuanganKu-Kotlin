package com.andreas.keuanganku.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreas.keuanganku.domain.model.CategoryModel
import com.andreas.keuanganku.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase
): ViewModel() {
    private val _categories = MutableStateFlow<List<CategoryModel>>(emptyList())
    val categories: StateFlow<List<CategoryModel>> get() = _categories

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            try {
                categoryUseCase.getAllCategories().collect { list ->
                    _categories.value = list
                }
            } catch (e: Exception) {
                _uiEvent.emit(UiEvent.SaveResult(false, "Gagal memuat kategori: ${e.message ?: "Tidak diketahui"}"))
            }
        }
    }

    fun addCategory(name: String, type: Int, color: String?) {
        viewModelScope.launch {
            try {
                val data = CategoryModel(
                    id = 0,
                    type= type,
                    name = name,
                    color = color
                )
                categoryUseCase.insertCategory(data)
                _uiEvent.emit(UiEvent.SaveResult(true, "Kategori berhasil ditambahkan"))
            } catch (e: Exception) {
                _uiEvent.emit(UiEvent.SaveResult(false, "Gagal menambahkan kategori: ${e.message ?: "Tidak diketahui"}"))
            }
        }
    }

    fun updateCategory(category: CategoryModel) {
        viewModelScope.launch {
            try {
                categoryUseCase.updateCategory(category)
                _uiEvent.emit(UiEvent.SaveResult(true, "Kategori berhasil diperbarui"))
            } catch (e: Exception) {
                _uiEvent.emit(UiEvent.SaveResult(false, "Gagal memperbarui kategori: ${e.message ?: "Tidak diketahui"}"))
            }
        }
    }

    fun deleteCategory(category: CategoryModel) {
        viewModelScope.launch {
            try {
                categoryUseCase.deleteCategory(category)
                _uiEvent.emit(UiEvent.SaveResult(true, "Kategori berhasil dihapus"))
            } catch (e: Exception) {
                _uiEvent.emit(UiEvent.SaveResult(false, "Gagal menghapus kategori: ${e.message ?: "Tidak diketahui"}"))
            }
        }
    }

}
