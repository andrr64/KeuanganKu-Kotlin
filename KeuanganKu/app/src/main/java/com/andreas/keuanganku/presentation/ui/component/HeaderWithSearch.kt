package com.andreas.keuanganku.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderWithSearch(
    title: String,
    description: String,
    isDarkTheme: Boolean,
    searchValue: String,
    onSearchChange: (String) -> Unit,
    searchPlaceholder: String = "Search...",
    filterContent: @Composable (() -> Unit)? = null
) {
    Header(
        title = title,
        description = description,
        isDarkTheme = isDarkTheme
    )
    Spacer(Modifier.height(16.dp))
    SearchField(
        value = searchValue,
        placeholder = searchPlaceholder,
        onValueChange = onSearchChange
    )
    Spacer(Modifier.height(16.dp))
    filterContent?.invoke()
    Spacer(Modifier.height(16.dp))
}
