package com.example.MyRoutine2.viewmodel//package com.example.plainolnotes4.del

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.MyRoutine2.NEW_ITEM_ID
import com.example.MyRoutine2.model.ItemEntity
import com.example.plainolitems4.data.AppDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemEditViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    //val itemsList = database?.itemDao()?.getAll()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //val sampleItems = SampleDataProvider.getItems()
//                val sampleItems = DummyContent.ITEMS_LIST
//                database?.itemDao()?.insertAll(sampleItems)

                //if it's new
                //database?.itemDao()?.getItemById(progragId)
                //else
                //insertItem?.itemDao()?.getItemById(progragId)



            }
        }
    }


    fun insertItem(nameString: String, itemId: Long, duration:Long? = null, pauseAfter: Boolean = false) {
        viewModelScope.launch {
            var item: ItemEntity? = null
            withContext(Dispatchers.IO) {
                if (itemId == NEW_ITEM_ID) {
                    item = ItemEntity(0,nameString,duration,pauseAfter)
                }
                else
                {item = database?.itemDao()?.getItemById(itemId)
                    item?.apply {
                        this.nameString = nameString
                        this.duration = duration
                        this.pauseAfter = pauseAfter

                    }}

                database?.itemDao()?.insertItem(item!!)
            }
        }
    }

}