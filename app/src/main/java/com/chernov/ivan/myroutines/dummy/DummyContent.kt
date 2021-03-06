package com.chernov.ivan.myroutines.dummy

import com.chernov.ivan.myroutines.model.Program
import com.chernov.ivan.myroutines.model.ProgramItem
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    val PROGRAMS_LIST: MutableList<Program> = ArrayList()

    //    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()
    val PROGRAM_MAP: MutableMap<String, Program> = HashMap()

    private val COUNT_ITMES = 25
    private val COUNT_PROGRAM = 5

    init {
        for (i in 1..COUNT_PROGRAM) {
            val itemsArrayList: MutableList<ProgramItem> = ArrayList()

            for (i2 in 1..COUNT_ITMES) {
                itemsArrayList.add(createDummyItem("$i _$i2"))
//                addItem(itemsArrayList,createDummyItem(i))
            }
            val program = Program(i.toString(),"name_$i", itemsArrayList)
            PROGRAMS_LIST.add(program) //for recyclerView
            PROGRAM_MAP.put(i.toString(), program) //for getting selected


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

    private fun createDummyItem(position: String): ProgramItem {
        //return DummyItem(position.toString(), "Item " + position, makeDetails(position))
        return ProgramItem(position,"Name_$position")
    }


    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    fun getItemsOfProgram(programID: String): MutableList<ProgramItem> {
        // TODO: 02.11.2020  non-null assertion
        val emptyItemList:MutableList<ProgramItem> = arrayListOf()
        return PROGRAM_MAP.get(programID)?.itemsArray ?:emptyItemList

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
