package com.example.akshay.databaseapp

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface userDao{

    @Query("SELECT * from TableData")
    fun getAll(): List<TableData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tableData: TableData)

    @Query("DELETE from TableData")
    fun deleteAll()
}
