package com.chernov.ivan.myroutines.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val name:String,
    val duration: Long =0,
    val programId: Int,
    val description:String = "default description") {
}