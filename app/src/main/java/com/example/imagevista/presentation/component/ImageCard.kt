package com.example.imagevista.presentation.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.imagevista.domain.model.UnsplashImage

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    image: UnsplashImage?
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(image?.imageUrlSmall)
        .crossfade(true)
        .build()
    val aspectRatio: Float by remember {
        derivedStateOf { (image?.width?.toFloat() ?: 1f) / (image?.height?.toFloat() ?: 1f) }
    }

   Card(
       shape = RoundedCornerShape(10.dp),
       modifier = Modifier
           .fillMaxWidth()
           .aspectRatio(aspectRatio)
           .then(modifier)
   ) {
       AsyncImage(
           model = imageRequest,
           contentDescription = null,
           contentScale = ContentScale.FillBounds,
           modifier = Modifier.fillMaxSize()
       )
   }
}