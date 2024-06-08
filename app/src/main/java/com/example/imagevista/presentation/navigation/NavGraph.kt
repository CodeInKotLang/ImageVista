package com.example.imagevista.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.imagevista.presentation.favorites_screen.FavoritesScreen
import com.example.imagevista.presentation.full_image_screen.FullImageScreen
import com.example.imagevista.presentation.home_screen.HomeScreen
import com.example.imagevista.presentation.home_screen.HomeViewModel
import com.example.imagevista.presentation.search_screen.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen
    ) {
        composable<Routes.HomeScreen> {
            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(
                scrollBehavior = scrollBehavior,
                images = viewModel.images,
                onImageClick = { imageId ->
                    navController.navigate(Routes.FullImageScreen(imageId))
                },
                onSearchClick = { navController.navigate(Routes.SearchScreen) },
                onFABClick = { navController.navigate(Routes.FavoritesScreen) }
            )
        }
        composable<Routes.SearchScreen> {
            SearchScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
        composable<Routes.FavoritesScreen> {
            FavoritesScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
        composable<Routes.FullImageScreen> { backStackEntry ->
            val imageId = backStackEntry.toRoute<Routes.FullImageScreen>().imageId
            FullImageScreen(
                imageId = imageId,
                onBackClick = { navController.navigateUp() }
            )
        }
        composable<Routes.ProfileScreen> {

        }
    }
}