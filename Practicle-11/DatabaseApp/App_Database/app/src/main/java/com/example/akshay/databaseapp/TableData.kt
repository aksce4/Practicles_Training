package com.example.akshay.databaseapp

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class TableData(@PrimaryKey(autoGenerate = true) var id: Long?,
                   @ColumnInfo(name = "Name") var name: String,
                   @ColumnInfo(name = "Birth Date") var bDate: String)
{
    constructor():this(null, "", "")
}