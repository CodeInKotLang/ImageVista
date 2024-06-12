package com.example.imagevista.data.util

import com.example.imagevista.BuildConfig

object Constants {

    const val IV_LOG_TAG = "ImageVistaLogs"

    const val API_KEY = BuildConfig.UNSPLASH_API_KEY
    const val BASE_URL = "https://api.unsplash.com/"

    const val IMAGE_VISTA_DATABASE = "unsplash_images.db"
    const val FAVORITE_IMAGE_TABLE = "favorite_images_table"

    const val ITEMS_PER_PAGE = 10
}