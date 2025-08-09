package com.andreas.keuangankuplus.data.di

import com.andreas.keuangankuplus.data.repository.GoalRepositoryImpl
import com.andreas.keuangankuplus.domain.repository.GoalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds // Artinya, kalau ada yang butuh GoalRepository (interface), kasih GogalRepositoryImpl (implementasi)
    @Singleton
    abstract fun bindGoalRepository(
        impl: GoalRepositoryImpl
    ): GoalRepository
}
