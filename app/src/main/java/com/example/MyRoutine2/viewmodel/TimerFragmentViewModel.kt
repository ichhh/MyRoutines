package com.example.MyRoutine2.viewmodel//package com.example.plainolitems4.del

import android.annotation.SuppressLint
import android.app.Application
import android.os.Looper
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.MyRoutine2.model.ItemEntity
import com.example.plainolitems4.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimerFragmentViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val currentItem = MutableLiveData<ItemEntity>()
    val prevItem = MutableLiveData<ItemEntity>()
    val nextItem = MutableLiveData<ItemEntity>()

    fun getItemById(itemId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val item = database?.itemDao()?.getItemById(itemId)
                currentItem.postValue(item)
                finePrevAndnextItems(item!!.id)
            }
        }
    }

    private fun finePrevAndnextItems(currentItemId: Long) {

        var prevItemT: ItemEntity? = null
        var nextItemT: ItemEntity? = null

        Log.d("==123==", "Looper.getMainLooper().getThread() == Thread.currentThread() ==${Looper.getMainLooper().getThread() == Thread.currentThread()}")


        val listOfItems = database?.itemDao()?.getAllValues()

        listOfItems?.forEachIndexed { index, itemEntity ->
            if (itemEntity.id == currentItemId) {

                if (index > 0) {
                    prevItemT = listOfItems[index - 1]
                }

                if (index + 1 <= listOfItems.count() - 1) {
                    nextItemT = listOfItems[index + 1]
                }

                return@forEachIndexed
            }

        }
        prevItemT.let { prevItem.postValue(it) }
        nextItemT.let { nextItem.postValue(it) }
    }


}