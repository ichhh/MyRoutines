package com.example.plainolitems4.utils


import com.example.MyRoutine2.model.ItemEntity

class SampleDataProvider {

    companion object {
//        private val sampleText1 = "A simple item"
//        private val sampleText2 = "A item a\nline feed"
//        private val sampleText3 = """
//            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque finibus, magna eget ullamcorper eleifend, neque justo cursus nibh, sit amet fermentum nisi dui sed justo. Nulla ac nisl ut nulla euismod mollis. Aenean ullamcorper eu odio a rutrum. Mauris eu augue tortor. Pellentesque erat justo, gravida sed maximus eu, faucibus at justo. Aliquam ut nulla consectetur odio vestibulum vulputate vel id est. Aliquam erat volutpat.
//
//            Fusce maximus sagittis dolor in tempor. Duis vehicula congue lectus eu lobortis. Integer placerat fermentum sapien, vel feugiat sapien pellentesque non. Integer nec nibh sit amet ex lacinia pretium sed et eros. Nam id consequat erat, eleifend mollis quam. In dictum lobortis quam vel tincidunt. Vestibulum non lobortis neque. Phasellus pharetra malesuada mauris eget blandit. Sed ornare nisl id nisl tristique placerat.
//        """.trimIndent()
//
//        private fun getDate(diff: Long): Date {
//            return Date(Date().time + diff)
//        }

//        fun getItems() = arrayListOf(
//            ItemEntity(getDate(0), sampleText1),
//            ItemEntity(getDate(1), sampleText2),
//            ItemEntity(getDate(2), sampleText3)
//        )


        fun getItemsMap(): MutableMap<String, Long?>  {
            var dummyItems = mutableMapOf<String,Long?>()
            dummyItems["Mexidol"] = 30
            dummyItems["Anki"] = 90
            dummyItems["Mediation"] = 900
            return dummyItems
        }

        fun getItems(): MutableList<ItemEntity>  {
            var dummyItems = mutableListOf<ItemEntity>()
            dummyItems.add(ItemEntity(0,"n1",10,false))
            dummyItems.add(ItemEntity(1,"n2",20,false))
            dummyItems.add(ItemEntity(2,"n3",30,false))
            dummyItems.add(ItemEntity(3,"n4",10,false))
            return dummyItems
        }


    }

    }
