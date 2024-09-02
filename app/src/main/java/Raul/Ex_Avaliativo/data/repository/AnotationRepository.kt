package Raul.Ex_Avaliativo.data.repository

import android.content.Context
import Raul.Ex_Avaliativo.data.database.AppDatabase
import Raul.Ex_Avaliativo.data.model.Anotation

class AnotationRepository(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val dao = database.getAnotationdao()
    suspend fun insert(anotation: Anotation): Boolean{
        return dao.create(anotation) > 0
    }
    suspend fun update(anotation: Anotation): Boolean{
        return dao.update(anotation) > 0
    }
    suspend fun remove(anotation: Anotation): Boolean{
        return dao.delete(anotation) > 0
    }
    suspend fun findById(id: Long): Anotation{
        return dao.getAnotation(id)
    }
    suspend fun findAll(): List<Anotation>{
        return dao.getAll()
    }
}