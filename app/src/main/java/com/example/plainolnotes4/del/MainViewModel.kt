package com.example.plainolnotes4.del

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plainolitems4.data.AppDatabase
import com.example.plainolnotes4.model.ItemEntity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val itemsList = database?.itemDao()?.getAll()

//    fun addSampleData() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val sampleItems = SampleDataProvider.getItems()
//                database?.itemDao()?.insertAll(sampleItems)
//            }
//        }
//    }

    fun deleteItems(selectedItems: List<ItemEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.itemDao()?.deleteItems(selectedItems)
            }
        }

    }

    fun deleteAllItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.itemDao()?.deleteAll()
            }
        }
    }

}