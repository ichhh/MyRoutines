package com.chernov.ivan.myroutines.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plainolnotes4.data.AppDatabase
import com.example.plainolnotes4.data.NoteEntity
import com.example.plainolnotes4.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProgramFragmentViewModel(app: Application) : AndroidViewModel(app) {

//    private val database = AppDatabase.getInstance(app)
//    val notesList = database?.noteDao()?.getAll()
//
//    fun addSampleData() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val sampleNotes = SampleDataProvider.getNotes()
//                database?.noteDao()?.insertAll(sampleNotes)
//            }
//        }
//    }
//
//    fun deleteNotes(selectedNotes: List<NoteEntity>) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                database?.noteDao()?.deleteNotes(selectedNotes)
//            }
//        }
//
//    }
//
//    fun deleteAllNotes() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                database?.noteDao()?.deleteAll()
//            }
//        }
//    }

}
