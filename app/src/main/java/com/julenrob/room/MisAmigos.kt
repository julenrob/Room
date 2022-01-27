package com.julenrob.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity @Serializable
data class MisAmigos(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "identificador")
        var id:Int = 0,
        var nombre:String = "",
        var email:String = ""
)

