package Raul.Ex_Avaliativo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import Raul.Ex_Avaliativo.data.model.Anotation
@Dao
interface Anotationdao {
    @Insert
    suspend fun create(anotation: Anotation): Long
    @Query("SELECT * FROM tb_diario ORDER BY local")
    suspend fun getAll(): List<Anotation>
    @Query("SELECT * FROM tb_diario WHERE id = :id")
    suspend fun getAnotation(id: Long): Anotation
    @Update
    suspend fun update(anotation: Anotation): Int
    @Delete
    suspend fun delete(anotation: Anotation): Int
}