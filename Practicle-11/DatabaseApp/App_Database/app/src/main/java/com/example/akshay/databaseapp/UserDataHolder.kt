package com.example.akshay.databaseapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(TableData::class), version = 1)
abstract class UserDataHolderClass : RoomDatabase() {

    abstract fun userDao(): userDao

}