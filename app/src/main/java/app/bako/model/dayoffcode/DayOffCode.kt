package app.bako.model.dayoffcode

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.bako.model.herit.AssignmentCode

@Entity(tableName = "dayoffcode")
class DayOffCode(
    @PrimaryKey(autoGenerate = false) val code: String,
    val color: String,
    val nbHour: Float
): AssignmentCode(code, color) {

}