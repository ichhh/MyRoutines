package com.example.MyRoutine2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "programs")
data class ProgramEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nameString: String,
    var itemsArray: MutableList<ItemEntity>
) {



    // TODO: 02.11.2020 works for List but not for ML
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//
//        if (javaClass != other?.javaClass) return false
//
//        other as Program
//
//        if (name != other.name) return false
//
//
//        if (!itemsArray.contentEquals(other.itemsArray)) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = name.hashCode()
//        result = 31 * result + itemsArray.contentHashCode()
//        return result
//    }
}