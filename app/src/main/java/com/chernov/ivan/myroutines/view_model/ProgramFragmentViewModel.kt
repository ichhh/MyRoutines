package com.chernov.ivan.myroutines.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.chernov.ivan.myroutines.data.temp.DummyContent
import com.chernov.ivan.myroutines.model.ProgramEntity
import com.chernov.ivan.myroutines.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProgramFragmentViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    val programsList = database?.programDao()?.getAll()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //val samplePrograms = SampleDataProvider.getPrograms()
                val samplePrograms = DummyContent.PROGRAMS_LIST
                database?.programDao()?.insertAll(samplePrograms)
            }
        }
    }

    fun deletePrograms(selectedPrograms: List<ProgramEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.programDao()?.deletePrograms(selectedPrograms)
            }
        }

    }

    fun deleteAllPrograms() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.programDao()?.deleteAll()
            }
        }
    }

}
