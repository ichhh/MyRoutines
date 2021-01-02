package com.example.plainolitems4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.MyRoutine2.ITEM_DB_NAME
import com.example.MyRoutine2.ITEM_DB_VERSION
import com.example.MyRoutine2.data.ItemDao
import com.example.MyRoutine2.model.ItemEntity

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