package com.example.plainolnotes4.del//package com.example.plainolnotes4.del
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.plainolitems4.data.AppDatabase
//
//import com.example.plainolnotes4.model.ItemEntity
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class EditorViewModel(app: Application) : AndroidViewModel(app) {
//
//    private val database = AppDatabase.getInstance(app)
//    val currentItem = MutableLiveData<ItemEntity>()
//
//    fun getItemById(itemId: Int) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val item =
//                    if (itemId != NEW_ITEM_ID) {
//                        database?.itemDao()?.getItemById(itemId)
//                    } else {
//                        ItemEntity()
//                    }
//                currentItem.postValue(item)
//            }
//        }
//    }
//
//    fun updateItem() {
//        currentItem.value?.let {
//            it.text = it.text.trim()
//            if (it.id == NEW_ITEM_ID && it.text.isEmpty()) {
//                return
//            }
//
//            viewModelScope.launch {
//                withContext(Dispatchers.IO) {
//                    if (it.text.isEmpty()) {
//                        database?.itemDao()?.deleteItem(it)
//                    } else {
//                        database?.itemDao()?.insertItem(it)
//                    }
//                }
//            }
//
//        }
//
//
//    }
//
//}