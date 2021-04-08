package app.bako.view.settings.workcode

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import app.bako.model.workcode.WorkCode
import java.text.SimpleDateFormat
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import android.graphics.LightingColorFilter

import android.graphics.ColorFilter

import android.graphics.Color

import android.graphics.drawable.Drawable
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import app.bako.R
import app.bako.view.navigation.popup.AddWorkCodePopup
import kotlinx.android.synthetic.main.workcode_item.view.*


class WorkCodeAdapter(var context: Context) :
    RecyclerView.Adapter<WorkCodeAdapter.ExampleViewHolder>() {
    private var workCodeList: List<WorkCode> = emptyList<WorkCode>()

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var codeAffectation: TextView
        var startHour: TextView
        var endHour: TextView
        var buttonEditWorkCode: ImageButton
        var layoutBackGround : LinearLayout
//        var color: TextView

        init {
            codeAffectation = itemView.findViewById(R.id.textview_workCode)
            startHour = itemView.findViewById(R.id.textview_workStartHour)
            endHour = itemView.findViewById(R.id.textview_workEndHour)
            layoutBackGround = itemView.findViewById(R.id.layout_bg)
//            color = itemView.findViewById(R.id.colorPicker)
            buttonEditWorkCode = itemView.findViewById(R.id.editWorkCode)
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
        val myIcon = ContextCompat.getDrawable(context, R.drawable.layout_bg)
        val filter: ColorFilter = LightingColorFilter(currentWorkCode.color, currentWorkCode.color)
        myIcon!!.colorFilter = filter
        holder.layoutBackGround.background = myIcon
        holder.buttonEditWorkCode.setOnClickListener{
            val addWorkCodePopup = AddWorkCodePopup()
            val bundle = Bundle()
            bundle.putParcelable("workCode", currentWorkCode)
            addWorkCodePopup.arguments = bundle
            val fm: FragmentManager = (context as AppCompatActivity).supportFragmentManager
            addWorkCodePopup.show(fm,"edit_fragment")
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

    fun setData(workCode: List<WorkCode>) {
        this.workCodeList = workCode
        notifyDataSetChanged()
//        notifyItemRangeInserted(0, workCodeList.size)
    }
}
