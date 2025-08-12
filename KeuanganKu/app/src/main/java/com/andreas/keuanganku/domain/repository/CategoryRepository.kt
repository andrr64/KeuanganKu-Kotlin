package com.andreas.keuanganku.domain.repository

import com.andreas.keuanganku.data.local.entity.CategoryEntity
import com.andreas.keuanganku.domain.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<CategoryModel>>
    suspend fun getCategoryById(id: Int): CategoryModel?
    suspend fun insertCategory(category: CategoryModel)
    suspend fun updateCategory(category: CategoryModel)
    suspend fun deleteCategory(category: CategoryModel)
}