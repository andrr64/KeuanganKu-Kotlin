package com.andreas.keuanganku.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.andreas.keuanganku.presentation.ui.component.BottomBar
import com.andreas.keuanganku.presentation.ui.features.goal.GoalScreen
import com.andreas.keuanganku.presentation.ui.features.home.HomeScreen
import com.andreas.keuanganku.presentation.ui.features.transaction.TransactionScreen
import com.andreas.keuanganku.presentation.ui.navigation.BottomNavItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()

    val useDarkIcons = !isDarkTheme
    val navBarColor = if (isDarkTheme) Color.Black else Color.White

    SideEffect {
        systemUiController.setNavigationBarColor(
            color = navBarColor,
            darkIcons = useDarkIcons
        )
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
    }

    val pages = listOf(
        BottomNavItem.Home,
        BottomNavItem.Transaction,
        BottomNavItem.Goal
    )
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        bottomBar = {
            BottomBar(
                selectedIndex = pagerState.currentPage,
                items = pages,
                onItemSelected = { index ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    ) { innerPadding ->
        HorizontalPager(
            pageSize = PageSize.Fill,
            state = pagerState,
            modifier = Modifier
                .padding(
//                    top = innerPadding.calculateTopPadding(),
//                    bottom = 80.dp
                    innerPadding
                )
        ) { page ->
            when (pages[page]) {
                BottomNavItem.Home -> HomeScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { }
                )

                BottomNavItem.Goal -> GoalScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { }
                )

                BottomNavItem.Transaction -> TransactionScreen(
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }
        }
    }
}
