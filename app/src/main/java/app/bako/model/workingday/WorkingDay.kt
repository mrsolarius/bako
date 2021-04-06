package app.bako.model.workingday

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import app.bako.model.workcode.WorkCode
import java.util.*

@Entity(tableName = "workingday")
data class WorkingDay(
        @PrimaryKey(autoGenerate = false) var date: Date
        ) {
    lateinit var prevWorkCode: String
    lateinit var realWorking: String
}