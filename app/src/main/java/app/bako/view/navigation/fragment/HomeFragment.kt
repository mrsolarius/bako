package app.bako.view.navigation.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.model.workingday.WorkingDayViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils


// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var chart: LineChart
    private lateinit var mWorkingDayViewModel: WorkingDayViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        chart = view.findViewById<LineChart>(R.id.chartNbHeureAnnee)

        chart.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
        resources
        chart.xAxis.textColor = getResources().getColor(R.color.colorAccent)
        chart.axisLeft.textColor = getResources().getColor(R.color.colorAccent)
        chart.axisRight.isEnabled = false

        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(false);

        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.legend.textColor = getResources().getColor(R.color.colorAccent)


        val mWorkingDayRepository = ViewModelProvider(this).get(WorkingDayViewModel::class.java)
        mWorkingDayRepository.getAllWorkingDayWithWorkCode().observe(viewLifecycleOwner, { workingDay ->
            workingDay.let {
                var hours: ArrayList<Int> = ArrayList()
                var totalHours = 0;
                it.forEach {
                    val hour = ((it.realWorkCode.endHour.time - it.realWorkCode.startHour.time)/1000/60/60).toInt()
                    totalHours += hour
                    hours.add(totalHours)
                }
                setData(view, hours)
                chart.notifyDataSetChanged()
                chart.animateX(300);
            }
        })

        val l = chart.legend

        // draw legend entries as lines

        // draw legend entries as lines
        l.form = LegendForm.LINE
        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setData(vue: View, value: ArrayList<Int>) {
        val values: ArrayList<Entry> = ArrayList()
        var position = 0
        for (i in value) {
            values.add(Entry(position.toFloat(), i.toFloat(), resources.getDrawable(R.drawable.star)))
            position+=1
        }
        val set1: LineDataSet
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = chart.getData().getDataSetByIndex(0) as LineDataSet
            set1.setValues(values)
            set1.notifyDataSetChanged()
            chart.getData().notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "")
            set1.setDrawIcons(false)
            set1.setDrawValues(false)

            set1.color = getResources().getColor(R.color.colorAccent)
            set1.setDrawCircles(false)

            // line thickness and point size
            set1.lineWidth = 3f
            set1.circleRadius = 0f

            set1.setLabel("Heure de travail");


            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            chart.setData(data)
        }
    }

}