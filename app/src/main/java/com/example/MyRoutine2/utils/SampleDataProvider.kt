package com.example.plainolitems4.utils


import com.example.MyRoutine2.model.ItemEntity
import com.example.MyRoutine2.model.ProgramEntity

class SampleDataProvider {

    companion object {

        fun getItemsMap(): MutableMap<String, Long?> {
            var dummyItems = mutableMapOf<String, Long?>()
            dummyItems["Mexidol"] = 30
            dummyItems["Anki"] = 90
            dummyItems["Mediation"] = 900
            return dummyItems
        }

        fun getItems(): MutableList<ItemEntity> {
            var dummyItems = mutableListOf<ItemEntity>()
            dummyItems.add(ItemEntity(0, "n1", 10, false))
            dummyItems.add(ItemEntity(1, "n2", 20, false))
            dummyItems.add(ItemEntity(2, "n3", 30, false))
            dummyItems.add(ItemEntity(3, "n4", 10, false))
            return dummyItems
        }

        fun getPrograms(): MutableList<ProgramEntity> {
            var dummyPrograms = mutableListOf<ProgramEntity>()
            dummyPrograms.add(ProgramEntity(0, "mor", getMorningItems()))
            dummyPrograms.add(ProgramEntity(1, "night", getNightItems()))

            return dummyPrograms
        }

        fun getMorningItems(): MutableList<ItemEntity> {
            var dummyItems = mutableListOf<ItemEntity>()
            dummyItems.add(ItemEntity(0, "m1", 10, false))
            dummyItems.add(ItemEntity(1, "m2", 20, false))
            dummyItems.add(ItemEntity(2, "m3", 30, false))
            dummyItems.add(ItemEntity(3, "m4", 10, false))
            return dummyItems
        }

        fun getNightItems(): MutableList<ItemEntity> {
            var dummyItems = mutableListOf<ItemEntity>()
            dummyItems.add(ItemEntity(0, "n1", 10, false))
            dummyItems.add(ItemEntity(1, "n2", 20, false))
            dummyItems.add(ItemEntity(2, "n3", 30, false))
            dummyItems.add(ItemEntity(3, "n4", 10, false))
            return dummyItems
        }
    }
}
