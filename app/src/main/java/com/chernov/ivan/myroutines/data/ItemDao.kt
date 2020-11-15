package com.chernov.ivan.myroutines.data

import androidx.lifecycle.LiveData
import androidx.room.*

import com.chernov.ivan.myroutines.model.ItemEntity

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(items: List<ItemEntity>)

//    @Query("SELECT * FROM items ORDER BY date ASC")
    @Query("SELECT * FROM items ORDER BY id ASC")
    fun getAll(): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItemById(id: Int): ItemEntity?

    @Query("SELECT COUNT(*) from items")
    fun getCount(): Int

    @Delete
    fun deleteItems(selectedItems: List<ItemEntity>): Int

    @Query("DELETE FROM items")
    fun deleteAll():Int

    @Delete
    fun deleteItem(item: ItemEntity)

}