package com.andreas.keuanganku.domain.usecase

import com.andreas.keuanganku.data.local.entity.CategoryEntity
import com.andreas.keuanganku.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(): Flow<List<CategoryEntity>> = repository.getAllCategories()
}

class GetCategoryByIdUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(id: Int): CategoryEntity? = repository.getCategoryById(id)
}

class InsertCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(category: CategoryEntity) = repository.insertCategory(category)
}

class UpdateCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(category: CategoryEntity) = repository.updateCategory(category)
}

class DeleteCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(category: CategoryEntity) = repository.deleteCategory(category)
}
