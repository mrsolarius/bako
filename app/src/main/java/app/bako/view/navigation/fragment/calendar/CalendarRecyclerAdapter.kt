package app.bako.view.navigation.fragment.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.model.relation.WorkingDayWithWorkCodes
import app.bako.model.workingday.WorkingDay
import kotlinx.android.synthetic.main.calendar_row.view.*

class CalendarRecyclerAdapter() :
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
            if (workingDay.prevWorkCode !=null && workingDay.realWorkCode ==null){
                itemView.real_work_layout.visibility = View.GONE
            }else if (workingDay.realWorkCode != null && workingDay.prevWorkCode==null){
                itemView.prev_work_layout.visibility = View.GONE
            }
            itemView.date_text.text = workingDay.workingDay.toString();
            itemView.real_work_code.text = workingDay.realWorkCode.code;
            itemView.prev_work_code.text = workingDay.prevWorkCode.code;
            itemView.prev_work_code.text = workingDay.prevWorkCode.code;
        }
    }

}