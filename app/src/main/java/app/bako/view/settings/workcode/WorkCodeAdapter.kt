package app.bako.view.settings.workcode

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import app.bako.model.workcode.WorkCode
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.LinearLayout
import app.bako.R
import app.bako.utils.DateFormat.Companion.hourToString
import app.bako.utils.UiUtils.Companion.getCustomDrawable
import app.bako.utils.UiUtils.Companion.isColorDark
import app.bako.view.settings.workcode.popup.AddWorkCodePopup
import kotlinx.android.synthetic.main.workcode_item.view.*

/**
 * WorkCodeAdapter
 * Classe adapter de la liste des code de travail
 */
class WorkCodeAdapter(var context: Context) :
    RecyclerView.Adapter<WorkCodeAdapter.WorkCodeViewHolder>() {
    private var workCodeList: List<WorkCode> = emptyList()

    /**
     * View Holder
     */
    class WorkCodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var codeAffectation: TextView = itemView.textview_workCode
        var startHour: TextView = itemView.textview_workStartHour
        var endHour: TextView = itemView.textview_workEndHour
        var pipe :TextView = itemView.pipe2
        var buttonEditWorkCode: ImageButton = itemView.editWorkCode
        var layoutBackGround : LinearLayout = itemView.layout_bg

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkCodeViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.workcode_item, parent, false)
        return WorkCodeViewHolder(v)
    }

    override fun onBindViewHolder(holder: WorkCodeViewHolder, position: Int) {
        val currentWorkCode: WorkCode = workCodeList[position]
        holder.codeAffectation.text = currentWorkCode.code
        holder.startHour.text = hourToString(currentWorkCode.startHour)
        holder.endHour.text = hourToString(currentWorkCode.endHour)
        holder.layoutBackGround.background = getCustomDrawable(currentWorkCode.color,context)
        if (isColorDark(currentWorkCode.color)){
            holder.codeAffectation.setTextColor(Color.WHITE)
            holder.startHour.setTextColor(Color.WHITE)
            holder.endHour.setTextColor(Color.WHITE)
            holder.pipe.setTextColor(Color.WHITE)
        }else{
            holder.codeAffectation.setTextColor(Color.BLACK)
            holder.startHour.setTextColor(Color.BLACK)
            holder.endHour.setTextColor(Color.BLACK)
            holder.pipe.setTextColor(Color.BLACK)
        }

        //Edit button listener
        holder.buttonEditWorkCode.setOnClickListener{
            val addWorkCodePopup = AddWorkCodePopup()
            val bundle = Bundle()
            bundle.putParcelable("workCode", currentWorkCode)
            addWorkCodePopup.arguments = bundle
            val fm: FragmentManager = (context as AppCompatActivity).supportFragmentManager
            addWorkCodePopup.show(fm,"edit_fragment")
        }
    }

    override fun getItemCount(): Int {
        return workCodeList.size
    }

    fun setData(workCode: List<WorkCode>) {
        this.workCodeList = workCode
        notifyDataSetChanged()
    }
}
