package com.andreas.keuanganku.presentation.ui.features.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andreas.keuanganku.presentation.ui.component.SearchField


@Composable
fun TransactionScreen(
    isDarkTheme: Boolean,
){
    var searchKeyword by remember { mutableStateOf("") }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
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
    }
}