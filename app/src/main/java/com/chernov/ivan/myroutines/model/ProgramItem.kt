package com.chernov.ivan.myroutines.model

import java.time.Duration

data class ProgramItem(val name:String, val duration: Long =0, val description:String = "default description") {
}