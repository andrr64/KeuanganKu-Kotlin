package com.andreas.keuanganku.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val color: String?
)