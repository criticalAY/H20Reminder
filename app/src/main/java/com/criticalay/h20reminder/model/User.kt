package com.criticalay.h20reminder.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name : String,
    var age: Int = 0,
    var weight: Int = 0,
    var gender: String = "",
    var volume: String = "",
    var water: Int = 0


)