package com.andreas.keuanganku.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreas.keuanganku.presentation.ui.MainScreen
import com.andreas.keuanganku.presentation.ui.features.transaction.AddTransactionScreen
import com.andreas.keuanganku.presentation.viewmodel.GoalViewModel
import com.andreas.keuanganku.presentation.viewmodel.TransactionViewModel


@Composable
fun AppNavGraph(navController: NavHostController, isDarkTheme: Boolean) {
    val transactionViewModel: TransactionViewModel = hiltViewModel()
    val goalViewModel: GoalViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable(
            route = AppScreenRoute.MAIN
        ) {
            MainScreen(
                isDarkTheme,
                navController,
                transactionViewModel = transactionViewModel,
                goalViewModel = goalViewModel
            )
        }

        composable(
            route= AppScreenRoute.ADD_TRANSACTION
        ) {
            AddTransactionScreen(
                navController = navController,
                viewModel = transactionViewModel
            )
        }
    }
}