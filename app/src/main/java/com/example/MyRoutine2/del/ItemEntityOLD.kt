package com.example.MyRoutine2.del

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntityOLD(
    @PrimaryKey(autoGenerate = true) val id:Int,
    var name:String,
    var duration: Long =0,
    var programId: Int,
    var description:String = "default description") {
}