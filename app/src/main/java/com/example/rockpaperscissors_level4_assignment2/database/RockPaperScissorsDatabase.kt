package com.example.rockpaperscissors_level4_assignment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockpaperscissors_level4_assignment2.models.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class RockPaperScissorsDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        private const val DATABASE_NAME = "ROCK_PAPER_SCISSORS_DATABASE"

        @Volatile
        private var rockPaperScissorsDatabaseInstance: RockPaperScissorsDatabase? = null

        /**
         * This function is responsible for creating the database object which we can use to access the database
         */
        fun getDatabase(context: Context): RockPaperScissorsDatabase? {
            if (rockPaperScissorsDatabaseInstance == null) {
                synchronized(RockPaperScissorsDatabase::class.java) {
                    if (rockPaperScissorsDatabaseInstance == null) {
                        rockPaperScissorsDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            RockPaperScissorsDatabase::class.java, DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return rockPaperScissorsDatabaseInstance
        }
    }

}
