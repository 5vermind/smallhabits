package com.h2ve.smallhabits.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.h2ve.smallhabits.model.Habit
import com.h2ve.smallhabits.model.RemoteKeys

@Database(
    entities = [Habit::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)

abstract class SmallHabitsDatabase : RoomDatabase() {

    abstract fun habitsDao(): HabitsDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        private const val DATABASE_NAME: String = "small_habits.db"
        const val TABLE_HABITS: String = "habits_data"

        fun create(context: Context) : SmallHabitsDatabase {
            return Room.databaseBuilder(context, SmallHabitsDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}