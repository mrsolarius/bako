package app.bako.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import app.bako.model.workcode.WorkCode
import app.bako.model.workingday.WorkingDay

/*
 * Table Pivot WorkingDayWithWorkCode
 *
 * Déclare la relation entre les working day et les Workcodes
 */
data class WorkingDayWithWorkCodes(
        @Embedded val workingDay: WorkingDay,
        @Relation(
            parentColumn = "prevWorkCode",
            entityColumn = "code"
        )
        val prevWorkCode: WorkCode,
        @Relation(
                parentColumn = "realWorking",
                entityColumn = "code"
        )
        val realWorkCode: WorkCode,
)