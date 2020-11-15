package com.chernov.ivan.myroutines.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.chernov.ivan.myroutines.data.AppDatabase
import com.chernov.ivan.myroutines.data.temp.DummyContent
import com.chernov.ivan.myroutines.model.ItemEntity



import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemFragmentViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    val itemsList = database?.itemDao()?.getAll()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleItems = DummyContent.getItemsOfProgram(1)
                database?.itemDao()?.insertAll(sampleItems)
            }
        }
    }

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