package com.andreas.keuanganku.data.repository

import com.andreas.keuanganku.data.local.dao.CategoryDao
import com.andreas.keuanganku.data.local.entity.CategoryEntity
import com.andreas.keuanganku.domain.model.CategoryModel
import com.andreas.keuanganku.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    private fun CategoryEntity.toDomain() = CategoryModel(
        id = id,
        name = name,
        type = type,
        color = color
    )

    private fun CategoryModel.toEntity() = CategoryEntity(
        id = id,
        name = name,
        type = type,
        color = color
    )

    override fun getAllCategories(): Flow<List<CategoryModel>> {
        return categoryDao.getAllCategories()
            .map { listEntity -> listEntity.map { it.toDomain() } }
    }

    override suspend fun getCategoryById(id: Int): CategoryModel? {
        return categoryDao.getCategoryById(id)?.toDomain()
    }

    override suspend fun insertCategory(category: CategoryModel) {
        categoryDao.insertCategory(category.toEntity())
    }

    override suspend fun updateCategory(category: CategoryModel) {
        categoryDao.updateCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: CategoryModel) {
        categoryDao.deleteCategory(category.toEntity())
    }
}
