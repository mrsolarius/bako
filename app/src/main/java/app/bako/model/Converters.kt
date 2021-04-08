package app.bako.model

import androidx.room.TypeConverter
import java.util.*

/**
 * Cette class s'occupe de converter automatiquement les objet pour insert dans la base de donnée. Ou reconvertir les données de la BDD en objet
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}