package com.criticalay.h20reminder.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Drunk")
data class Drink(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: String,
    var time: String,
    var drink: String,
    var amount: Int,

    )