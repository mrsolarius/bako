package app.bako.view.navigation.fragment.calendar.plannig

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.model.relation.WorkingDayWithWorkCodes
import app.bako.utils.DateFormat.Companion.getFormattedDay
import app.bako.utils.DateFormat.Companion.getFormattedTime
import app.bako.utils.UiUtils.Companion.getCustomDrawable
import app.bako.utils.UiUtils.Companion.isColorDark
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
            itemView.date_text.text = getFormattedDay(workingDay.workingDay.date)

            itemView.real_work_code.text = workingDay.realWorkCode.code
            itemView.real_work_hours.text = getFormattedTime(workingDay.realWorkCode)
            itemView.real_work_layout.background = getCustomDrawable(workingDay.realWorkCode.color,itemView.context)
            if (isColorDark(workingDay.realWorkCode.color)) {
                itemView.real_work_code.setTextColor(Color.WHITE)
                itemView.real_work_hours.setTextColor(Color.WHITE)
                itemView.real.setTextColor(Color.WHITE)
            }
            else{
                itemView.real_work_code.setTextColor(Color.BLACK)
                itemView.real_work_hours.setTextColor(Color.BLACK)
                itemView.real.setTextColor(Color.BLACK)
            }


            itemView.prev_work_code.text = workingDay.prevWorkCode.code
            itemView.prev_work_hours.text = getFormattedTime(workingDay.prevWorkCode)
            itemView.prev_work_layout.background = getCustomDrawable(workingDay.prevWorkCode.color,itemView.context)
            if (isColorDark(workingDay.prevWorkCode.color)) {
                itemView.prev_work_code.setTextColor(Color.WHITE)
                itemView.prev_work_hours.setTextColor(Color.WHITE)
                itemView.prev.setTextColor(Color.WHITE)
            }else {
                itemView.prev_work_code.setTextColor(Color.BLACK)
                itemView.prev_work_hours.setTextColor(Color.BLACK)
                itemView.prev.setTextColor(Color.BLACK)
            }
        }




    }

}