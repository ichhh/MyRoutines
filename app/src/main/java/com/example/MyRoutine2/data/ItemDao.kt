package com.example.MyRoutine2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.MyRoutine2.model.ItemEntity

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(items: List<ItemEntity>)

    // TODO: 01.01.2021 ascending by orderposition
    @Query("SELECT * FROM items")
    //@Query("SELECT * FROM items ORDER BY id ASC")
    fun getAll(): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItemById(id: Long): ItemEntity?

    @Query("SELECT COUNT(*) from items")
    fun getCount(): Int

    @Delete
    fun deleteItems(selectedItems: List<ItemEntity>): Int

    @Query("DELETE FROM items")
    fun deleteAll():Int

    @Delete
    fun deleteItem(item: ItemEntity)

}