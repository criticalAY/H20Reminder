package com.criticalay.h20reminder.model

import androidx.room.ColumnInfo

class Sum {
    data class Sum(
        var date: String = "",
        @ColumnInfo(name = "Total")
        var total: Int = 0
    )
}