package app.bako.model.workcode

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.bako.model.herit.AssignmentCode
import java.util.*

@Entity(tableName = "workcode")
data class WorkCode(
    @PrimaryKey(autoGenerate = false) val code:String?,
    val color: Int,
    val startHour: Date,
    val endHour: Date
): AssignmentCode(
    code, color
), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readSerializable() as Date,
        parcel.readSerializable() as Date
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeInt(color)
        parcel.writeSerializable(startHour)
        parcel.writeSerializable(endHour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WorkCode> {
        override fun createFromParcel(parcel: Parcel): WorkCode {
            return WorkCode(parcel)
        }

        override fun newArray(size: Int): Array<WorkCode?> {
            return arrayOfNulls(size)
        }
    }

}