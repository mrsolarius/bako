package app.bako.model.workcode

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.bako.model.herit.AssignmentCode
import java.util.*

@Entity(tableName = "workcode")
data class WorkCode(
    @PrimaryKey(autoGenerate = false) val code:String,
    val color:String,
    val startHour: Date,
    val endHour: Date
): AssignmentCode(code, color) {

}