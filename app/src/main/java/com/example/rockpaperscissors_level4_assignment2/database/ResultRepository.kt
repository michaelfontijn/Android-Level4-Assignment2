package com.example.rockpaperscissors_level4_assignment2.database

import android.content.Context
import com.example.rockpaperscissors_level4_assignment2.models.Result

class ResultRepository(context: Context) {

    private var resultDao: ResultDao

    init {
        val reminderRoomDatabase = RockPaperScissorsDatabase.getDatabase(context)
        resultDao = reminderRoomDatabase!!.resultDao()
    }

    fun getAllResults(): List<Result>{
        return resultDao.getAllMatchResults()
    }

    fun insertResult(result :Result){
        resultDao.insertMatchResult(result)
    }

    fun deleteAllResults(){
        resultDao.deleteAllMatchResults()
    }

}
