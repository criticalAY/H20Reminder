package com.criticalay.h20reminder.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.criticalay.h20reminder.utils.AppDatabase
import com.criticalay.h20reminder.utils.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrinkViewModel (application: Application):AndroidViewModel(application){



    val readAllData : LiveData<List<Drink>>
    private val repository : Repository

    init {
        val drinkDao = AppDatabase.getDatabase(application).dao()
        repository = Repository(drinkDao)
        readAllData = repository.realAllData


    }







    fun addDrink(drink: Drink){
viewModelScope.launch (Dispatchers.IO){


    repository.addDrink(drink)
}


    }
}