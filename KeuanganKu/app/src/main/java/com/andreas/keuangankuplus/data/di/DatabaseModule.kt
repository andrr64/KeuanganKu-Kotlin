package com.andreas.keuangankuplus.data.di

import android.content.Context
import androidx.room.Room
import com.andreas.keuangankuplus.data.local.AppDatabase
import com.andreas.keuangankuplus.data.local.GoalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "keuanganku_db")
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideGoalDao(database: AppDatabase): GoalDao = database.goalDao()
}
