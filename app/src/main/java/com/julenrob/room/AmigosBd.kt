package com.julenrob.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MisAmigos::class],
    version = 1
)

abstract class AmigosBd: RoomDatabase(){
    abstract fun MisAmigosDAO(): MisAmigosDAO
}
