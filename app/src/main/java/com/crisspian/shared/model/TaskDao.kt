package com.crisspian.shared.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    //metodos o acciones de la base de datos
    // agregar palabra reservada suspend ,indica al sistema
    // que estos metodos se ejecutaran asincronos por corutinas , hilo secundario de rutinas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTask(taskList: List<Task>)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
   suspend fun deleteAllTask()

    @Query("SELECT * FROM task_table WHERE id = :mId")
    //envolver objeto en Live data ,convierte objetos en vistas
    fun getTaskByID(mId: Int): LiveData<Task>

    @Query("SELECT * FROM task_table")
    fun getAllTask(): LiveData<List<Task>>

}