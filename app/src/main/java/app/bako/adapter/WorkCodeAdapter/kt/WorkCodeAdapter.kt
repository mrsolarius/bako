package app.bako.adapter.WorkCodeAdapter.kt

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.model.WorkCode
import app.bako.view.navigation.MainActivity
import app.bako.view.navigation.popup.AddWorkCodePopup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class WorkCodeAdapter(context: Context, workCodeList: ArrayList<WorkCode>) :
    RecyclerView.Adapter<WorkCodeAdapter.ExampleViewHolder>() {
    private val workCodeList: ArrayList<WorkCode>
    private val context: Context

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var codeAffectation: TextView
        var startHour: TextView
        var endHour: TextView
        var buttonEditWorkCode: FloatingActionButton
//        var color: TextView

        init {
            codeAffectation = itemView.findViewById(R.id.textview_workCode)
            startHour = itemView.findViewById(R.id.textview_workStartHour)
            endHour = itemView.findViewById(R.id.textview_workEndHour)
//            color = itemView.findViewById(R.id.colorPicker)
            buttonEditWorkCode = itemView.findViewById<FloatingActionButton>(R.id.editWorkCode)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.workcode_item, parent, false)

        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentWorkCode: WorkCode = workCodeList[position]
        holder.codeAffectation.setText(currentWorkCode.code)
        holder.startHour.setText(HourToString(currentWorkCode.startHour))
        holder.endHour.setText(HourToString(currentWorkCode.endHour))
//        holder.color.setText(currentWorkCode.color)
        holder.buttonEditWorkCode.setOnClickListener{
            val addWorkCodePopup = AddWorkCodePopup()
            val bundle = Bundle()
            bundle.putParcelable("workCode", currentWorkCode)
            addWorkCodePopup.arguments = bundle

            val activityObject: MainActivity = context as MainActivity
            activityObject.makeCurrentFragment(addWorkCodePopup)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun HourToString(date: Date): String {
        val formatter = SimpleDateFormat("HH:mm")
        val strDate: String = formatter.format(date)
        return strDate
    }

    override fun getItemCount(): Int {
        return workCodeList.size
    }

    init {
        this.context = context
        this.workCodeList = workCodeList
    }
}
