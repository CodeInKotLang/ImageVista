package com.example.imagevista.presentation.favorites_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imagevista.domain.model.UnsplashImage
import com.example.imagevista.domain.repository.ImageRepository
import com.example.imagevista.presentation.util.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val _snackbarEvent = Channel<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    val favoriteImages: StateFlow<PagingData<UnsplashImage>> = repository.getAllFavoriteImages()
        .catch { exception ->
            _snackbarEvent.send(
                SnackbarEvent(message = "Something went wrong. ${exception.message}")
            )
        }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = PagingData.empty()
        )

    val favoriteImageIds: StateFlow<List<String>> = repository.getFavoriteImageIds()
        .catch { exception ->
            _snackbarEvent.send(
                SnackbarEvent(message = "Something went wrong. ${exception.message}")
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = emptyList()
        )

    fun toggleFavoriteStatus(image: UnsplashImage) {
        viewModelScope.launch {
            try {
                repository.toggleFavoriteStatus(image)
            } catch (e: Exception) {
                _snackbarEvent.send(
                    SnackbarEvent(message = "Something went wrong. ${e.message}")
                )
            }
        }
    }

}