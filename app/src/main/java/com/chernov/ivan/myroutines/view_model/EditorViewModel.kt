package com.chernov.ivan.myroutines.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.chernov.ivan.myroutines.EditorFragmentArgs
import com.chernov.ivan.myroutines.data.AppDatabase
import com.chernov.ivan.myroutines.databinding.EditorFragmentBinding
import com.chernov.ivan.myroutines.model.ItemEntity
import com.chernov.ivan.myroutines.util.NEW_ENTITY_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EditorViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val currentItem = MutableLiveData<ItemEntity>()

    fun getItemById(itemId: Int, programId: Int = 0) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val item =
                    if (itemId != NEW_ENTITY_ID) {
                        database?.itemDao()?.getItemById(itemId)
                    } else {
                        ItemEntity(0,"",0,programId)
                    }
                currentItem.postValue(item)
            }
        }
    }

    fun updateItem() {
        currentItem.value?.let {
            it.name = it.name.trim()
            if (it.id == NEW_ENTITY_ID && it.name.isEmpty()) {
                return
            }

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    if (it.name.isEmpty()) {
                        database?.itemDao()?.deleteItem(it)
                    } else {
                        database?.itemDao()?.insertItem(it)
                    }
                }
            }

            // TODO: 15.11.2020 change programm, duration, type

        }


    }

}
