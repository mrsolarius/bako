package app.bako.model.workcode

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.bako.model.herit.AssignmentCode
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "workcode")
data class WorkCode(
    @PrimaryKey(autoGenerate = false) var code:String,
    var color: Int,
    var startHour: Date,
    var endHour: Date
): Parcelable, AssignmentCode(
    code, color
)