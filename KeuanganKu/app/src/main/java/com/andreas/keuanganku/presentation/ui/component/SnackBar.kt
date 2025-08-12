package com.andreas.keuanganku.presentation.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable

@Composable
fun CustomSnackbar(
    data: SnackbarData,
    isError: Boolean = false
) {
    Snackbar(
        snackbarData = data,
        containerColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondaryContainer,
        contentColor = if (isError) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onSecondaryContainer
    )
}