package com.example.imagevista.domain.repository

import com.example.imagevista.domain.model.UnsplashImage

interface ImageRepository {

    suspend fun getEditorialFeedImages(): List<UnsplashImage>

    suspend fun getImage(imageId: String): UnsplashImage
}