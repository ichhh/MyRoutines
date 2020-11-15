package com.chernov.ivan.myroutines.data.temp

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
    val PROGRAM_MAP: MutableMap<Int, ProgramEntity> = HashMap()

    private val COUNT_ITMES = 25
    private val COUNT_PROGRAM = 5

    init {
        for (i in 1..COUNT_PROGRAM) {
            val itemsArrayList: MutableList<ProgramItemEntity> = ArrayList()

            for (i2 in 1..COUNT_ITMES) {
                itemsArrayList.add(createDummyItem(i+i2))
//                addItem(itemsArrayList,createDummyItem(i))
            }
            val program = ProgramEntity(i, "name_$i", itemsArrayList)
            PROGRAMS_LIST.add(program) //for recyclerView
            PROGRAM_MAP.put(i, program) //for getting selected




            }
    }

//    private fun addItem(itemsArrayList:MutableList<ProgramItem>, item: ProgramItem) {
//        itemsArrayList.add(item)
////        ITEM_MAP.put(item.id, item)
//    }

    //    private fun addProgram(program: Program) {
//        PROGRAMS.add(program)
////        ITEM_MAP.put(program.id, program)
//    }
//    private fun createDummyProgram(
//        position: Int,
//        itemsArrayList: MutableList<ProgramItem>
//    ): Program {
//        return Program(position.toString(), itemsArrayList)
//    }

    private fun createDummyItem(position: Int): ProgramItemEntity {
        //return DummyItem(position.toString(), "Item " + position, makeDetails(position))
        return ProgramItemEntity(position, "Name_$position")
    }


    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    fun getItemsOfProgram(programID: Int = 2): MutableList<ProgramItemEntity> {
        // TODO: 02.11.2020  non-null assertion
        val emptyItemList: MutableList<ProgramItemEntity> = arrayListOf()
        return PROGRAM_MAP[programID]?.itemsArray ?: emptyItemList
    }

    /**
     * A dummy item representing a piece of content.
     */
//    data class DummyItem(val id: String, val content: String, val details: String) {
//        override fun toString(): String = content
//    }
//
//    data class DummyProgram(
//        val id: String,
//        val name: String,
//        val itemsArray: MutableList<ProgramItem>
//    ) {
//        override fun toString(): String = name
//    }

}
