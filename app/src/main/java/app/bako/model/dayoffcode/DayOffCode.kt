package app.bako.model.dayoffcode

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.bako.model.herit.AssignmentCode
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "dayoffcode")
class DayOffCode(
    @PrimaryKey(autoGenerate = false) val code: String?,
    val color: Int,
    val nbHour: Float
): AssignmentCode(
    code,
    color
), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeInt(color)
        parcel.writeFloat(nbHour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DayOffCode> {
        override fun createFromParcel(parcel: Parcel): DayOffCode {
            return DayOffCode(parcel)
        }

        override fun newArray(size: Int): Array<DayOffCode?> {
            return arrayOfNulls(size)
        }
    }

}