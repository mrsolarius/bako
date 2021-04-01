package app.bako.model

import java.sql.Time
import java.util.*

class WorkCode(var code:String, var color:String, var startHour: Date, var endHour: Date):AssignmentCode(code, color) {
}