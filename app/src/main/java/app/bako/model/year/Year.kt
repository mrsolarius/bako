package app.bako.model.year

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.bako.model.workingday.WorkingDay

@Entity(tableName = "year")
data class Year(
        @PrimaryKey val yearLabel: Int,
        val totalWorkingHour: Float
)