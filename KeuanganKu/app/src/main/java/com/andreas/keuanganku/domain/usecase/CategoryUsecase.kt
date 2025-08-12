package com.andreas.keuanganku.domain.usecase

import com.andreas.keuanganku.data.local.CategoryEntity
import com.andreas.keuanganku.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUsecase @Inject constructor(
    private val repository: CategoryRepository
) {
    fun getAllCategories(): Flow<List<CategoryEntity>> {
        return repository.getAllCategories()
    }

    suspend fun getCategoryById(id: Int): CategoryEntity? {
        return repository.getCategoryById(id)
    }

    suspend fun insertCategory(category: CategoryEntity) {
        repository.insertCategory(category)
    }

    suspend fun updateCategory(category: CategoryEntity) {
        repository.updateCategory(category)
    }

    suspend fun deleteCategory(category: CategoryEntity) {
        repository.deleteCategory(category)
    }
}
