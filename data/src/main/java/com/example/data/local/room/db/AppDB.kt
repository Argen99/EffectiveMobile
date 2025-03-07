package com.example.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.room.dao.CourseDao
import com.example.data.local.room.entity.CourseEntity

@Database(entities = [CourseEntity::class], version = 1)
abstract class AppDB: RoomDatabase() {
    abstract fun courseDao(): CourseDao
}