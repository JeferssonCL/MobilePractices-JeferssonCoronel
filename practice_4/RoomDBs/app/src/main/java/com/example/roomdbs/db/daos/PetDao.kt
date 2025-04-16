package com.example.roomdbs.db.daos

import androidx.room.*
import com.example.roomdbs.db.models.Pet

@Dao
interface PetDao {
    @Query("SELECT * FROM Pet")
    fun getAll(): List<Pet>

    @Query("SELECT * FROM Pet WHERE id = :id")
    fun getById(id: Int): Pet

    @Query("SELECT * FROM Pet WHERE personId = :personId")
    fun getByPersonId(personId: Int): List<Pet>

    @Insert
    fun insertPet(pet: Pet)

    @Update
    fun updatePet(pet: Pet)

    @Delete
    fun deletePet(pet: Pet)
}
