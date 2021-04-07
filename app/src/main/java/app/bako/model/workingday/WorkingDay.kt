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
    var prevWorkCode: String? = null
    var realWorking: String? = null
}