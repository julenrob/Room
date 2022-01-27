package com.julenrob.room

import androidx.room.*

@Dao
interface MisAmigosDAO {
    @Query("SELECT * from MisAmigos")
    suspend fun getTodo():List<MisAmigos>

    @Query("SELECT * from MisAmigos WHERE identificador = :id")
    suspend fun getPorId(id: Int): MisAmigos

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(amigos: MisAmigos)

    @Query("UPDATE MisAmigos SET nombre = :newName, email = :newEmail WHERE identificador = :amigoId")
    suspend fun update(amigoId: String, newName:String, newEmail:String)

    @Query("DELETE FROM MisAmigos WHERE identificador = :amigoId")
    suspend fun delete(amigoId: String)

}