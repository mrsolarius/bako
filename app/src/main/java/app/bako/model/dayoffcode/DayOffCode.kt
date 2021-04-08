package app.bako.model.dayoffcode

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.bako.model.herit.AssignmentCode
import kotlinx.android.parcel.Parcelize

/**
 * Table DayOffCode
 */

@Parcelize
@Entity(tableName = "dayoffcode")
class DayOffCode(
    @PrimaryKey(autoGenerate = false) val code: String,
    val color: Int,
    val nbHour: Float
    //Transformation de l'objet pour le passer dans les bundle
): Parcelable, AssignmentCode(
    code,
    color
)