package app.bako.view.navigation.fragment.calendar.plannig

import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.model.relation.WorkingDayWithWorkCodes
import app.bako.view.navigation.fragment.calendar.utils.dateFormat.Companion.getFormattedDay
import app.bako.view.navigation.fragment.calendar.utils.dateFormat.Companion.getFormattedTime
import kotlinx.android.synthetic.main.calendar_row.view.*


class CalendarRecyclerAdapter(var context: CalendarFragment) :
    RecyclerView.Adapter<CalendarRecyclerAdapter.ViewHolder>() {

    private var workDayList: List<WorkingDayWithWorkCodes> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.calendar_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workingDay = workDayList[position]
        holder.bind(workingDay)
    }

    override fun getItemCount(): Int = workDayList.size


    fun setData(workingDayList: List<WorkingDayWithWorkCodes>) {
        workDayList = workingDayList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(workingDay: WorkingDayWithWorkCodes){
            val realWorkBG = ContextCompat.getDrawable(itemView.context, R.drawable.layout_bg)
            val realWorkFilter: ColorFilter = LightingColorFilter(workingDay.realWorkCode.color, workingDay.realWorkCode.color)
            realWorkBG!!.colorFilter = realWorkFilter

            val prevWorkBG = ContextCompat.getDrawable(itemView.context, R.drawable.layout_bg)
            val prevWorkFilter: ColorFilter = LightingColorFilter(workingDay.prevWorkCode.color, workingDay.prevWorkCode.color)
            prevWorkBG!!.colorFilter = prevWorkFilter

            itemView.date_text.text = getFormattedDay(workingDay.workingDay.date);

            itemView.real_work_code.text = workingDay.realWorkCode.code;
            itemView.real_work_hours.text = getFormattedTime(workingDay.realWorkCode)
            itemView.real_work_layout.background = realWorkBG

            itemView.prev_work_code.text = workingDay.prevWorkCode.code;
            itemView.prev_work_hours.text = getFormattedTime(workingDay.prevWorkCode);
            itemView.prev_work_layout.background = prevWorkBG

        }


    }

}