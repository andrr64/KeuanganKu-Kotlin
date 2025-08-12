package com.andreas.keuanganku.domain.model

data class CategoryModel(
    val id: Int,
    val name: String,
    val type: String, // contoh: pemasukan/pengeluaran
    val color: String?
)
