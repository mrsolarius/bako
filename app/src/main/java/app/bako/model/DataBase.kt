package app.bako.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.bako.model.dayoffcode.DayOffCode
import app.bako.model.dayoffcode.DayOffCodeDao
import app.bako.model.workcode.WorkCode
import app.bako.model.workcode.WorkCodeDao

@Database(entities = [WorkCode::class, DayOffCode::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DataBase: RoomDatabase() {

    abstract fun WorkCodeDao(): WorkCodeDao
    abstract fun DayOffCodeDao(): DayOffCodeDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "bako_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}