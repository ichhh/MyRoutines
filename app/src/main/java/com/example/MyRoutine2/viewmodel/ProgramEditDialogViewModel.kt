package com.example.MyRoutine2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.MyRoutine2.NEW_ENTITY_ID
import com.example.MyRoutine2.model.ItemEntity
import com.example.MyRoutine2.model.ProgramEntity
import com.example.plainolitems4.data.AppDatabase
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

    fun insertProgram(programName: String, programId: Int) {
        viewModelScope.launch {
            var program: ProgramEntity? = null
            withContext(Dispatchers.IO) {
                if (programId == NEW_ENTITY_ID) {
                    program = ProgramEntity(0,programName,
                        mutableListOf<ItemEntity>()
                    )
                }
                else
                {program = database?.programDao()?.getProgramById(programId)
                    program?.nameString = programName}

                database?.programDao()?.insertProgram(program!!)
             }
        }
    }




}