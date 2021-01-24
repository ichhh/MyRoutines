package com.example.MyRoutine2.viewmodel//package com.example.plainolitems4.del

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.MyRoutine2.model.ItemEntity
import com.example.plainolitems4.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimerFragmentViewModel(app: Application) : AndroidViewModel(app) {

    var prevItemName: String = ""
    var nextItemName: String = ""

    private val database = AppDatabase.getInstance(app)
    val currentItem = MutableLiveData<ItemEntity>()

    fun getItemById(itemId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val item = database?.itemDao()?.getItemById(itemId)
                currentItem.postValue(item)
                finePrevAndNextItemNames2(item!!.id)
            }
        }
    }

    private fun finePrevAndNextItemNames(currentItemId: Long): List<String> {

        var list: List<String> = listOf()

        var prevItemNameT = ""
        var nextItemNameT = ""
        var lastItemNameT: String? = null

        var itemIsFound = false

        database?.itemDao()?.getAll()!!.value?.forEach {

            if (itemIsFound) {
                nextItemNameT = it.nameString
                return listOf(prevItemNameT, nextItemNameT)
            } else {
                if (it.id == currentItemId) {
                    itemIsFound = true
                    prevItemNameT = lastItemNameT ?: ""
                } else {
                    lastItemNameT = it.nameString
                }
            }
        }

        return list
    }

    private fun finePrevAndNextItemNames2(currentItemId: Long) {

        var list: List<String> = listOf()

        var prevItemNameT = ""
        var nextItemNameT = ""
        var lastItemNameT: String? = null

        var itemIsFound = false

        database?.itemDao()?.getAll()!!.value?.forEach {

            if (itemIsFound) {
                nextItemNameT = it.nameString

                prevItemName = prevItemNameT
                nextItemName = nextItemNameT
                return

            } else {
                if (it.id == currentItemId) {
                    itemIsFound = true
                    prevItemNameT = lastItemNameT ?: ""
                } else {
                    lastItemNameT = it.nameString
                }
            }
        }
    }


}