package com.example.plainolitems4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plainolnotes4.ITEM_DB_NAME
import com.example.plainolnotes4.ITEM_DB_VERSION
import com.example.plainolnotes4.data.ItemDao
import com.example.plainolnotes4.del.DateConverter
import com.example.plainolnotes4.model.ItemEntity

@Database(entities = [ItemEntity::class], version = ITEM_DB_VERSION, exportSchema = false)
//@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao?

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        ITEM_DB_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}

//@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
//@TypeConverters(DateConverter::class)
//abstract class AppDatabase: RoomDatabase() {
//
//    abstract fun itemDao(): ItemDao?
//
//    companion object {
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase? {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDatabase::class.java,
//                        "plainolitems.db"
//                    ).build()
//                }
//            }
//            return INSTANCE
//        }
//    }
//}