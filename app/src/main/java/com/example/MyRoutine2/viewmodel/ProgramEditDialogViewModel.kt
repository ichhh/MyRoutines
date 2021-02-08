package com.example.MyRoutine2.viewmodel//package com.example.plainolitems4.del

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.MyRoutine2.NEW_ITEM_ID
import com.example.MyRoutine2.NEW_ITEM_ID_INT

import com.example.MyRoutine2.model.ProgramEntity
import com.example.plainolitems4.data.AppDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProgramEditDialogViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val currentProgram = MutableLiveData<ProgramEntity>()


    fun getProgramById(programId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val program =
                    if (programId != NEW_ITEM_ID_INT) {
                        database?.programDao()?.getProgramById(programId)
                    } else {
                        ProgramEntity(programId,"", mutableListOf())
                    }
                currentProgram.postValue(program)
            }
        }
    }

    fun insertProgram(nameString: String, programId: Int, duration:Long? = null, pauseAfter: Boolean = false) {
        viewModelScope.launch {
            var program: ProgramEntity? = null
            withContext(Dispatchers.IO) {
                if (programId == NEW_ITEM_ID_INT) {

                    program = ProgramEntity(NEW_ITEM_ID_INT,nameString, mutableListOf())
                }
                else
                {program = database?.programDao()?.getProgramById(programId)
                    program?.apply {
                        this.nameString = nameString
//                        this.duration = duration
//                        this.pauseAfter = pauseAfter

                    }}

                database?.programDao()?.insertProgram(program!!)
            }
        }
    }

    fun updateProgram() {
        currentProgram.value?.let {
            it.nameString = it.nameString.trim()
            if (it.id == NEW_ITEM_ID_INT && it.nameString.isEmpty()) {
                return
            }

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    if (it.nameString.isEmpty()) {
                        database?.programDao()?.deleteProgram(it)
                    } else {
                        database?.programDao()?.insertProgram(it)
                    }
                }
            }

        }


    }


}