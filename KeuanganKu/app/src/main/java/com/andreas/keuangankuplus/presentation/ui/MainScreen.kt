package com.andreas.keuangankuplus.presentation.ui // Sesuaikan dengan package-mu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andreas.keuangankuplus.presentation.ui.component.BottomBar
import com.andreas.keuangankuplus.presentation.ui.features.goal.GoalScreen
import com.andreas.keuangankuplus.presentation.ui.features.home.HomeScreen
import com.andreas.keuangankuplus.presentation.ui.navigation.BottomNavItem
import com.andreas.keuangankuplus.presentation.ui.theme.KeuanganKuTheme
import com.andreas.keuangankuplus.presentation.viewmodel.GoalViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    var isDarkTheme by remember { mutableStateOf(false) }
    KeuanganKuTheme(darkTheme = isDarkTheme) {
        // 1. Menggunakan NavController khusus animasi dari Accompanist
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph(
                    navController = navController,
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDarkTheme = !isDarkTheme }
                )
            }
        }
    }
}

/**
 * Composable yang berisi semua rute tujuan (NavHost)
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(
            route = BottomNavItem.Home.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
        ) {
            HomeScreen(
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }

        composable(
            route = BottomNavItem.Goal.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
        ) {
            val viewModel: GoalViewModel = hiltViewModel()
            GoalScreen(
                isDarkTheme = isDarkTheme,
                viewModel = viewModel,
                onThemeChange = onThemeChange
            )
        }
    }
}