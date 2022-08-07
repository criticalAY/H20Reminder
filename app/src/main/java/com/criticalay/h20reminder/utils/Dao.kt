package com.criticalay.h20reminder.utils

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.criticalay.h20reminder.model.Drink
import com.criticalay.h20reminder.model.Sum

import com.criticalay.h20reminder.model.User


private const val TABLE_NAME_DRUNK = "Drunk"
private const val COL_ID_DRUNK = "id"
private const val COL_DATE_DRUNK = "date"
private const val COL_TIME_DRUNK = "time"
private const val COL_DRINK_DRUNK = "drink"
private const val COL_AMOUNT_DRUNK = "amount"
private const val COL_METRIC_DRUNK = "metric"


private const val TABLE_NAME = "User"

@Dao
interface Dao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertDrinkData(drink: Drink)

    @Query("SELECT * FROM Drunk  ORDER BY id DESC")
    fun readDrinkDataDetailsSelectedDay(): LiveData<List<Drink>>

//    @Query("SELECT * FROM Drunk WHERE date = :dates ORDER BY id DESC ")
//    suspend  fun dateRecord(dates : String) : MutableList<Drink>










}