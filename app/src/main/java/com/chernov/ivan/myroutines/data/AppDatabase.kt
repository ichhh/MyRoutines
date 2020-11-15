package com.chernov.ivan.myroutines.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chernov.ivan.myroutines.ProgramDao
import com.chernov.ivan.myroutines.data.temp.DateConverter
import com.chernov.ivan.myroutines.model.ProgramEntity
import com.chernov.ivan.myroutines.model.ItemEntity
import com.chernov.ivan.myroutines.util.DB_VERSION

@Database(entities = [ProgramEntity::class, ItemEntity::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(DateConverter::class,ConverterGson::class)

abstract class AppDatabase: RoomDatabase() {

    abstract fun programDao(): ProgramDao?
    abstract fun itemDao(): ItemDao?

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
                    )
                        .fallbackToDestructiveMigration() // TODO: 15.11.2020  provide a Migration in the builder
                        .build()
                }
            }
            return INSTANCE
        }
    }
}