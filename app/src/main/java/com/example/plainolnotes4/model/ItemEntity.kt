package com.example.plainolnotes4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.plainolnotes4.fastadapter.SwipeableDrawerItem


@Entity(tableName = "items",
//    ignoredColumns = arrayOf("picture")
)
data class ItemEntity (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var nameString:String = "",
var duration: Long? = null,
var pauseAfter: Boolean = false
)
    //: SwipeableDrawerItem()
{
}


//@Entity(tableName = "items")
//data class ItemEntity(
//    @PrimaryKey(autoGenerate = true) val id:Int,
//    var name:String,
//    var duration: Long =0,
//    var programId: Int,
//    var description:String = "default description") {
//}
//
//
//@Parcelize
//@Entity(tableName = "notes")
//data class NoteEntity(
//    @PrimaryKey(autoGenerate = true)
//    var id: Int,
//    var date: Date,
//    var text: String
//) : Parcelable {
//    constructor() : this(NEW_NOTE_ID, Date(), "")
//    constructor(date: Date, text: String) : this(NEW_NOTE_ID, date, text)
//}