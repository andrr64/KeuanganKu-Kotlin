package com.andreas.keuanganku.data.di

import com.andreas.keuanganku.data.repository.GoalRepositoryImpl
import com.andreas.keuanganku.domain.repository.GoalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds // Artinya, kalau ada yang butuh GoalRepository (interface), kasih GogalRepositoryImpl (implementasi)
    @Singleton
    abstract fun bindGoalRepository(
        impl: GoalRepositoryImpl
    ): GoalRepository
}
