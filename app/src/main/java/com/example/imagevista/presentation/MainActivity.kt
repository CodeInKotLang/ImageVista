package com.example.imagevista.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.imagevista.presentation.home_screen.HomeScreen
import com.example.imagevista.presentation.home_screen.HomeViewModel
import com.example.imagevista.presentation.navigation.NavGraphSetup
import com.example.imagevista.presentation.navigation.Routes
import com.example.imagevista.presentation.theme.ImageVistaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageVistaTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                val viewModel = viewModel<HomeViewModel>()
                HomeScreen(
                    scrollBehavior = scrollBehavior,
                    images = viewModel.images,
                    onImageClick = {},
                    onSearchClick = {},
                    onFABClick = {}
                )
            }
        }
    }
}
