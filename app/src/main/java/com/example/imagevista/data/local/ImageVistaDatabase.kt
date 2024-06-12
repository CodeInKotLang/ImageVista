package com.example.imagevista.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imagevista.data.local.entity.FavoriteImageEntity

@Database(
    entities = [FavoriteImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ImageVistaDatabase: RoomDatabase() {
    abstract fun favoriteImagesDao(): FavoriteImagesDao
}