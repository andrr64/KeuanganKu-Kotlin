package com.andreas.keuanganku.domain.usecase

import com.andreas.keuanganku.data.local.entity.CategoryEntity
import com.andreas.keuanganku.domain.model.CategoryModel
import com.andreas.keuanganku.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    fun getAllCategories(): Flow<List<CategoryModel>> = repository.getAllCategories()
    suspend fun getCategoryById(id: Int): CategoryModel? = repository.getCategoryById(id)
    suspend fun insertCategory(category: CategoryModel) = repository.insertCategory(category)
    suspend fun updateCategory(category: CategoryModel) = repository.updateCategory(category)
    suspend fun deleteCategory(category: CategoryModel) = repository.deleteCategory(category)
}
