package com.chernov.ivan.myroutines.model

data class Program(val id:String, val name:String, val itemsArray:MutableList<ProgramItem> ) {
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