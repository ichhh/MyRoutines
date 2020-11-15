package com.chernov.ivan.myroutines.data.temp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chernov.ivan.myroutines.util.NEW_ENTITY_ID
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Date,
    var text: String
) : Parcelable {
    constructor() : this(NEW_ENTITY_ID, Date(), "")
    constructor(date: Date, text: String) : this(NEW_ENTITY_ID, date, text)
}
