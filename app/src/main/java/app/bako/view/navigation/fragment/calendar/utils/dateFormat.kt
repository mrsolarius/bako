package app.bako.view.navigation.fragment.calendar.utils

import android.content.res.Resources
import android.icu.text.DateFormat.MEDIUM
import android.icu.text.DateFormat.getDateInstance
import android.text.format.DateFormat
import androidx.core.os.ConfigurationCompat
import app.bako.Bako
import app.bako.model.workcode.WorkCode
import java.text.DateFormat.getTimeInstance
import java.text.SimpleDateFormat
import java.util.*

class dateFormat {
    companion object {
        fun getFormattedTime(workCode: WorkCode): String {
            return DateFormat.getTimeFormat(Bako.context).format(workCode.startHour) + " - " + DateFormat.getTimeFormat(Bako.context).format(workCode.endHour);
        }

        fun getFormattedDay(date : Date):String{
            val sdf = SimpleDateFormat("d MMM" , ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0))
            return sdf.format(date.time)
        }
    }
}