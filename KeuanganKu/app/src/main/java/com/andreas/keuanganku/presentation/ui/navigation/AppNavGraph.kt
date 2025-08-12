package com.andreas.keuanganku.presentation.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreas.keuanganku.presentation.ui.MainScreen
import com.andreas.keuanganku.presentation.ui.features.category.AddCategoryScreen


@Composable
fun AppNavGraph(navController: NavHostController, isDarkTheme: Boolean) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable(
            route = "main",
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down) }
        ) {
            MainScreen(isDarkTheme, navController)
        }
        composable(
            route = "add_category",
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down) }
        ) { backStackEntry ->
            AddCategoryScreen(
                navController = navController
            )
        }
    }
}