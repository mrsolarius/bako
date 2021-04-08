package app.bako.utils

import android.content.res.Resources
import android.text.format.DateFormat
import androidx.core.os.ConfigurationCompat
import app.bako.Bako
import app.bako.model.workcode.WorkCode
import java.text.SimpleDateFormat
import java.util.*

class DateFormat {
    companion object {
        fun getFormattedTime(workCode: WorkCode): String {
            return DateFormat.getTimeFormat(Bako.context).format(workCode.startHour) + " - " + DateFormat.getTimeFormat(Bako.context).format(workCode.endHour);
        }

        fun getFormattedDay(date : Date):String{
            val sdf = SimpleDateFormat("d MMM" , ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0))
            return sdf.format(date.time)
        }

        fun getFormattedDate(date : Date):String{
            val sdf = SimpleDateFormat("dd/MM/yyyy" , ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0))
            return sdf.format(date.time)
        }

        fun stringToDate(str : String): Date? {
            val sdf = SimpleDateFormat("dd/MM/yyyy" , ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0))
            return sdf.parse(str)
        }

        fun hourToString(date: Date): String {
            val formatter = SimpleDateFormat("HH:mm")
            return formatter.format(date)
        }

        fun stringToHour(strToConvert: String): Date? {
            val formatter = SimpleDateFormat("HH:mm", Locale.FRANCE)
            return formatter.parse(strToConvert)
        }
    }
}