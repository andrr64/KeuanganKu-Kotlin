package com.andreas.keuanganku.data.di

import com.andreas.keuanganku.data.repository.CategoryRepositoryImpl
import com.andreas.keuanganku.data.repository.GoalRepositoryImpl
import com.andreas.keuanganku.domain.repository.CategoryRepository
import com.andreas.keuanganku.domain.repository.GoalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindGoalRepository(
        impl: GoalRepositoryImpl
    ): GoalRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository   // <- ini harus interface, bukan class implementasi
}
