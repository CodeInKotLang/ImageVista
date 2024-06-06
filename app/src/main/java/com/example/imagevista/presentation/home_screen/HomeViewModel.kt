package com.example.imagevista.presentation.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagevista.data.mapper.toDomainModelList
import com.example.imagevista.data.remote.dto.UnsplashImageDto
import com.example.imagevista.di.AppModule
import com.example.imagevista.domain.model.UnsplashImage
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    var images: List<UnsplashImage> by mutableStateOf(emptyList())
        private set

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            val result = AppModule.retrofitService.getEditorialFeedImages()
            images = result.toDomainModelList()
        }
    }
}