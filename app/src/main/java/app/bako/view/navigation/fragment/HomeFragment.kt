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
import app.bako.R
import com.github.mikephil.charting.charts.LineChart
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

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        chart = view.findViewById<LineChart>(R.id.chartNbHeureAnnee)

        chart.setBackgroundColor(Color.WHITE)

        chart.description.isEnabled=false

        chart.setTouchEnabled(true)

        chart.setOnChartValueSelectedListener(null)
        chart.setDrawGridBackground(false)

//        val mv = MarkerView(context, R.layout.c)

        //add data
        setData(view, 45, 180);

        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setData(vue:View, count: Int, range: Int) {
        val values: ArrayList<Entry> = ArrayList()
        for (i in 0 until count) {
            val valueEntry = (Math.random() * range).toFloat() - 30
            values.add(Entry(i.toFloat(), valueEntry, resources.getDrawable(R.drawable.star)))
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
            set1 = LineDataSet(values, "DataSet 1")
            set1.setDrawIcons(false)

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f)

            // black lines and points
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)

            // line thickness and point size
            set1.lineWidth = 1f
            set1.circleRadius = 3f

            // draw points as solid circles
            set1.setDrawCircleHole(false)

            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            // text size of values
            set1.valueTextSize = 9f

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter = IFillFormatter { dataSet, dataProvider -> chart.getAxisLeft().getAxisMinimum() }

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(vue.context, R.drawable.fade_red)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }
            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            chart.setData(data)
        }
    }

}