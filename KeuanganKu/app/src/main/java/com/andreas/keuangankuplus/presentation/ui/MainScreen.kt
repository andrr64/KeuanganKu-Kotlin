package com.andreas.keuangankuplus.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.andreas.keuangankuplus.presentation.ui.component.BottomBar
import com.andreas.keuangankuplus.presentation.ui.features.goal.GoalScreen
import com.andreas.keuangankuplus.presentation.ui.features.home.HomeScreen
import com.andreas.keuangankuplus.presentation.ui.navigation.BottomNavItem
import com.andreas.keuangankuplus.presentation.ui.theme.KeuanganKuTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    var isDarkTheme by remember { mutableStateOf(true) }
    val pages = listOf(
        BottomNavItem.Home,
        BottomNavItem.Goal
    )
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val coroutineScope = rememberCoroutineScope()

    KeuanganKuTheme(darkTheme = isDarkTheme) {
        Scaffold(
            bottomBar = {
                BottomBar(
                    selectedIndex = pagerState.currentPage,
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
                modifier = Modifier.padding(innerPadding)
            ) { page ->
                when (pages[page]) {
                    BottomNavItem.Home -> HomeScreen(
                        isDarkTheme = isDarkTheme,
                        onThemeChange = { isDarkTheme = !isDarkTheme }
                    )

                    BottomNavItem.Goal -> GoalScreen(
                        isDarkTheme = isDarkTheme,
                        onThemeChange = { isDarkTheme = !isDarkTheme }
                    )
                }
            }
        }
    }
}