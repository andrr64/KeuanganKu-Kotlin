package com.andreas.keuanganku.presentation.ui.features.transaction

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andreas.keuanganku.presentation.ui.component.ExpandableFabMenu
import com.andreas.keuanganku.presentation.ui.component.FabOption
import com.andreas.keuanganku.presentation.ui.component.SearchField
import com.andreas.keuanganku.presentation.ui.modal.ModalAddCategory
import com.andreas.keuanganku.presentation.viewmodel.TransactionViewModel
import com.andreas.keuanganku.presentation.viewmodel.UiEvent

@Composable
fun TransactionScreen(
    isDarkTheme: Boolean,
    navController: NavController
) {
    var searchKeyword by remember { mutableStateOf("") }
    var showModal by remember { mutableStateOf(false) }
    val viewModel: TransactionViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            if (event is UiEvent.SaveResult) {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Definisikan opsi FAB
    val fabOptions = listOf(
        FabOption(
            text = "Add Transaction",
            icon = { Icon(Icons.Default.AccountCircle, null) },
            onClick = {}
        ),
        FabOption(
            text = "Add Category",
            icon = { Icon(Icons.Default.Menu, null) },
            onClick = {
                showModal = true
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
                Text(
                    text = "My Transactions",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Quickly track your spending and progress towards your financial goals.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isDarkTheme) Color.Gray else Color.DarkGray
                )
                Spacer(Modifier.height(16.dp))
                SearchField(
                    value = searchKeyword,
                    placeholder = "Search transaction...",
                    onValueChange = { searchKeyword = it }
                )
            }
        }

        if (showModal) {
            ModalAddCategory(
                onDismiss = { showModal = false },
                onSave = { name, type, color ->
                    viewModel.addCategory(name, type, color)
                    showModal = false
                }
            )
        }
    }
}
