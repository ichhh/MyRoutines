package com.example.plainolnotes4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chernov.ivan.myroutines.ProgramDao
import com.chernov.ivan.myroutines.model.ProgramEntity

@Database(entities = [ProgramEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun programDao(): ProgramDao?

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "routines.db"
                        // TODO: 14.11.2020 extract db name. values?
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}