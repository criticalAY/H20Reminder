package com.criticalay.h20reminder.utils

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.criticalay.h20reminder.model.Drink
import com.criticalay.h20reminder.model.User
import java.text.SimpleDateFormat
import java.util.*

class Repository (private val userDao: Dao){
    private  val today= SimpleDateFormat("dd-MM-yyyy").format(Date())

        val realAllData : LiveData<List<Drink>> = userDao.readDrinkDataDetailsSelectedDay()


//    suspend fun todayData(date: String): MutableList<Drink>? {
//        return userDao?.dateRecord(date)
//    }


    suspend fun addDrink(drink: Drink)
       {

               userDao.insertDrinkData(drink)
       }


}