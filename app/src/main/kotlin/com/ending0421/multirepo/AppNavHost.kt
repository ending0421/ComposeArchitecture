package com.ending0421.multirepo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ending0421.multirepo.impl.SpecificScreen
import com.ending0421.multirepo.impl.SpecificViewModel
import com.ending0421.multirepo.result.CommonErrorDisplayView
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "specific_screen"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("specific_screen") {
            val viewModel: SpecificViewModel = koinViewModel<SpecificViewModel>()
            CommonErrorDisplayView(commonError = viewModel.commonError) {
                SpecificScreen(viewModel = viewModel)
            }
        }
        // 添加更多的 screen
    }
}