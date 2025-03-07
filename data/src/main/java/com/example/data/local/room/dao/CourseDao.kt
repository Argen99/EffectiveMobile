package com.example.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.room.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses")
    fun getCourses(): Flow<List<CourseEntity>>

    @Query("SELECT COUNT(*) FROM courses WHERE id = :id")
    suspend fun isCourseExists(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(course: CourseEntity)

    @Delete
    suspend fun delete(course: CourseEntity)
}