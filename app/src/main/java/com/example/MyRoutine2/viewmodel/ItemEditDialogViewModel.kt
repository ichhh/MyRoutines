package com.example.MyRoutine2.viewmodel//package com.example.plainolitems4.del

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.MyRoutine2.NEW_ITEM_ID
import com.example.MyRoutine2.model.ItemEntity
import com.example.plainolitems4.data.AppDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemEditDialogViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val currentItem = MutableLiveData<ItemEntity>()


    fun getItemById(itemId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val item =
                    if (itemId != NEW_ITEM_ID) {
                        database?.itemDao()?.getItemById(itemId)
                    } else {
                        ItemEntity()
                    }
                currentItem.postValue(item)
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

    fun updateItem() {
        currentItem.value?.let {
            it.nameString = it.nameString.trim()
            if (it.id == NEW_ITEM_ID && it.nameString.isEmpty()) {
                return
            }

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    if (it.nameString.isEmpty()) {
                        database?.itemDao()?.deleteItem(it)
                    } else {
                        database?.itemDao()?.insertItem(it)
                    }
                }
            }

        }


    }
    
    
}