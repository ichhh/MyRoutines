package com.chernov.ivan.myroutines.model

data class Program(val name:String, val itemsArray:Array<Item> ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Program

        if (name != other.name) return false
        if (!itemsArray.contentEquals(other.itemsArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + itemsArray.contentHashCode()
        return result
    }
}