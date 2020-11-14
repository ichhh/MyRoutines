package com.chernov.ivan.myroutines.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration

@Entity
data class ProgramItemEntity(@PrimaryKey(autoGenerate = true) val id:Int, val name:String, val duration: Long =0, val description:String = "default description") {
}