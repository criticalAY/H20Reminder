package com.criticalay.h20reminder.utils

import androidx.lifecycle.LiveData
import com.criticalay.h20reminder.model.Drink
import com.criticalay.h20reminder.model.User

class Repository (private val userDao: Dao){

        val realAllData : LiveData<List<Drink>> = userDao.readDrinkDataDetailsSelectedDay()

       suspend fun addDrink(drink: Drink)
       {

               userDao.insertDrinkData(drink)
       }


}