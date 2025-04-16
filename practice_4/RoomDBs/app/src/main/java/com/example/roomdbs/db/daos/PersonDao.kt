package com.example.roomdbs.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdbs.db.models.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM Person WHERE id = :id")
    fun getById(id: Int): Person

    @Insert
    fun insertPerson(person: Person)

    @Update
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)
}