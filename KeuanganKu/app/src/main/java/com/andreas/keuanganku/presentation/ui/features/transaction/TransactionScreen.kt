package com.andreas.keuanganku.presentation.ui.features.transaction

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andreas.keuanganku.presentation.ui.component.ExpandableFabMenu
import com.andreas.keuanganku.presentation.ui.component.FabOption
import com.andreas.keuanganku.presentation.ui.component.HeaderWithSearch
import com.andreas.keuanganku.presentation.ui.modal.ModalAddCategory
import com.andreas.keuanganku.presentation.ui.modal.ModalAddTransaction
import com.andreas.keuanganku.presentation.viewmodel.TransactionViewModel
import com.andreas.keuanganku.presentation.viewmodel.UiEvent

@Composable
fun TransactionScreen(
    isDarkTheme: Boolean,
    navController: NavController,
    viewModel: TransactionViewModel
) {
    var searchKeyword by remember { mutableStateOf("") }
    var showModalAddCategory by remember { mutableStateOf(false) }
    var showModalAddTransaction by remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            if (event is UiEvent.SaveResult) {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    val fabOptions = listOf(
        FabOption(
            text = "Add Transaction",
            icon = { Icon(Icons.Default.AccountCircle, null) },
            onClick = {
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("transaction_data", "myTransactionObject")
                navController.navigate("add_transaction")
            }
        ),
        FabOption(
            text = "Add Category",
            icon = { Icon(Icons.Default.Menu, null) },
            onClick = {
                showModalAddCategory = true
            }
        )
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            ExpandableFabMenu(options = fabOptions)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                HeaderWithSearch(
                    title = "My Transactions",
                    description = "Quickly track your spending and progress towards your financial goals.",
                    isDarkTheme = isDarkTheme,
                    searchValue = searchKeyword,
                    onSearchChange = { searchKeyword = it },
                    searchPlaceholder = "Search transaction..."
                )
            }

        }

        if (showModalAddCategory) {
            ModalAddCategory(
                onDismiss = { showModalAddCategory = false },
                onSave = { name, type, color ->
                    viewModel.addCategory(name, type, color)
                    showModalAddCategory = false
                }
            )
        }

        if (showModalAddTransaction){
            ModalAddTransaction(
                onDismiss = {showModalAddTransaction = false},
                onSave = { name, price, datetime, categoryId ->

                }
            )
        }
    }
}
