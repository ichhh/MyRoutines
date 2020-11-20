package com.chernov.ivan.myroutines.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chernov.ivan.myroutines.data.AppDatabase
import com.chernov.ivan.myroutines.data.temp.DummyContent
import com.chernov.ivan.myroutines.model.ProgramEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProgramEditDialogViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    //val programsList = database?.programDao()?.getAll()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //val samplePrograms = SampleDataProvider.getPrograms()
//                val samplePrograms = DummyContent.PROGRAMS_LIST
//                database?.programDao()?.insertAll(samplePrograms)

                //if it's new
                //database?.programDao()?.getProgramById(progragId)
                //else
                //insertProgram?.programDao()?.getProgramById(progragId)



            }
        }
    }

}