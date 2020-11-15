package com.chernov.ivan.myroutines.data

import androidx.lifecycle.LiveData
import androidx.room.*

import com.chernov.ivan.myroutines.model.ProgramItemEntity

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ProgramItemEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(Items: List<ProgramItemEntity>)

//    @Query("SELECT * FROM items ORDER BY date ASC")
    @Query("SELECT * FROM items ORDER BY id ASC")
    fun getAll(): LiveData<List<ProgramItemEntity>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItemById(id: Int): ProgramItemEntity?

    @Query("SELECT COUNT(*) from items")
    fun getCount(): Int

    @Delete
    fun deleteItems(selectedItems: List<ProgramItemEntity>): Int

    @Query("DELETE FROM items")
    fun deleteAll():Int

    @Delete
    fun deleteItem(Item: ProgramItemEntity)

}