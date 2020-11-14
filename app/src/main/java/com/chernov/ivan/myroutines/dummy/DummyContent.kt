package com.chernov.ivan.myroutines.dummy

import com.chernov.ivan.myroutines.model.ProgramEntity
import com.chernov.ivan.myroutines.model.ProgramItemEntity
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    val PROGRAMS_LIST: MutableList<ProgramEntity> = ArrayList()
    val PROGRAM_MAP: MutableMap<String, ProgramEntity> = HashMap()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, ProgramItem> = HashMap()

    private val COUNT = 25

    init {
        for (i in 1..COUNT_PROGRAM) {
            val itemsArrayList: MutableList<ProgramItemEntity> = ArrayList()

            for (i2 in 1..COUNT_ITMES) {
                itemsArrayList.add(createDummyItem(i+i2))
//                addItem(itemsArrayList,createDummyItem(i))
            }
            val program = ProgramEntity(i, "name_$i", itemsArrayList)
            PROGRAMS_LIST.add(program) //for recyclerView
            PROGRAM_MAP.put(i.toString(), program) //for getting selected


        }
    }

    private fun createDummyItem(position: Int): ProgramItem {
        return ProgramItem(position.toString(), "Name " + position)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
//    data class DummyItem(val id: String, val content: String, val details: String) {
//        override fun toString(): String = content
//    }
}
