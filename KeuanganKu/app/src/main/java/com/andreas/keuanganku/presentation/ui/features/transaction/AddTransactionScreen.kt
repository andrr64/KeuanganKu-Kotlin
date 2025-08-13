package com.andreas.keuanganku.presentation.ui.features.transaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andreas.keuanganku.presentation.ui.component.Header
import com.andreas.keuanganku.presentation.ui.component.input.InputDateTime
import com.andreas.keuanganku.presentation.ui.component.input.InputDropdown
import com.andreas.keuanganku.presentation.ui.component.input.InputNumericField
import com.andreas.keuanganku.presentation.ui.component.input.InputTextField
import com.andreas.keuanganku.presentation.viewmodel.TransactionViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    navController: NavController,
    viewModel: TransactionViewModel,
    isDarkTheme: Boolean
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableDoubleStateOf(0.0) }
    var dateTime by remember { mutableStateOf("") }
    var selectedTypeIndex by remember { mutableIntStateOf(0) }
    val typeOptions = listOf("Income", "Expense")

    val categoriesState = viewModel.categories.collectAsState()
    val allCategories = categoriesState.value
    val filteredCategories = allCategories.filter { it.type == selectedTypeIndex }
    var selectedCategoryIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Header(
                title = "New Transaction",
                description = "Record a new income or expense.",
                isDarkTheme = isDarkTheme
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputTextField(
                value = name,
                onValueChange = { name = it },
                label = "Transaction Name"
            )
            Spacer(modifier = Modifier.height(16.dp))

            InputNumericField(
                value = price,
                onValueChange = { price = it },
                label = "Price"
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputDateTime(
                value = dateTime,
                onValueChange = { dateTime = it },
                label = "Date & Time"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown Jenis
            InputDropdown(
                label = "Type",
                options = typeOptions,
                selectedIndex = selectedTypeIndex,
                onOptionSelected = {
                    selectedTypeIndex = it
                    selectedCategoryIndex = 0 // reset kategori saat jenis berubah
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown Kategori (filtered sesuai jenis)
            InputDropdown(
                label = "Category",
                options = filteredCategories.map { it.name },
                selectedIndex = selectedCategoryIndex,
                onOptionSelected = { selectedCategoryIndex = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { navController.popBackStack() }) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (name.isNotBlank() && dateTime.isNotBlank()) {
                            filteredCategories.getOrNull(selectedCategoryIndex)?.id
                            selectedTypeIndex // 0 = pemasukan, 1 = pengeluaran
                            viewModel.addTransaction(
                                name = name,
                                price = price,
                                datetime = dateTime,
                                categoryId = allCategories[selectedCategoryIndex].id
                            )
                            navController.popBackStack()
                        }
                    }
                ) {
                    Text("Save Transaction")
                }
            }
        }
    }
}

