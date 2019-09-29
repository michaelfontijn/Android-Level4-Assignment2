package com.example.rockpaperscissors_level4_assignment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rockpaperscissors_level4_assignment2.models.Result

@Dao
interface ResultDao{
    @Query("SELECT * FROM result")
    fun getAllMatchResults(): List<Result>

    @Insert
    fun insertMatchResult(result: Result)

    @Query("DELETE FROM result")
    fun deleteAllMatchResults()
}