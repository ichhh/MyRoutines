package com.chernov.ivan.myroutines

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chernov.ivan.myroutines.model.ProgramEntity

@Dao
interface ProgramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProgram(program: ProgramEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(programs: List<ProgramEntity>)

    //@Query("SELECT * FROM programs ORDER BY date ASC")
    @Query("SELECT * FROM programs ORDER BY id ASC")
    fun getAll(): LiveData<List<ProgramEntity>>

    @Query("SELECT * FROM programs WHERE id = :id")
    fun getProgramById(id: Int): ProgramEntity?

    @Query("SELECT COUNT(*) from programs")
    fun getCount(): Int

    @Delete
    fun deletePrograms(selectedPrograms: List<ProgramEntity>): Int

    @Query("DELETE FROM programs")
    fun deleteAll():Int

    @Delete
    fun deleteProgram(Program: ProgramEntity)

}