package com.example.imagevista.presentation.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.imagevista.data.remote.dto.UnsplashImageDto
import com.example.imagevista.domain.model.UnsplashImage

@Composable
fun HomeScreen(
    images: List<UnsplashImage>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        images.forEach { image ->
            Text(text = image.id)
            Text(text = image.imageUrlRegular, color = Color.Blue)
            Text(text = image.photographerName, fontWeight = FontWeight.Bold)
            Text(text = "${image.width}x${image.height}")
            HorizontalDivider()
        }
    }
}