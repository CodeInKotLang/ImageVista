package com.example.imagevista.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImageDto(
    val id: String,
    val description: String?,
    val height: Int,
    val width: Int,
    val urls: UrlsDto,
    val user: UserDto,
)

@Serializable
data class UrlsDto(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)