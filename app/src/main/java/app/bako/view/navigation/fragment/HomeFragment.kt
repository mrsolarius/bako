package app.bako.view.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.bako.R
import app.bako.model.workingday.WorkingDayViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class HomeFragment : Fragment() {

    private lateinit var chart: LineChart

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        chart = view.findViewById(R.id.chartNbHeureAnnee)

        chart.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        resources
        chart.xAxis.textColor = resources.getColor(R.color.colorAccent)
        chart.axisLeft.textColor = resources.getColor(R.color.colorAccent)
        chart.axisRight.isEnabled = false

        chart.setDrawGridBackground(false)
        chart.description.isEnabled = false
        chart.setDrawBorders(false)

        chart.setTouchEnabled(false)
        chart.isDragEnabled = false
        chart.setScaleEnabled(false)
        chart.legend.textColor = resources.getColor(R.color.colorAccent)

        //récupération des données de la BDD
        val mWorkingDayRepository = ViewModelProvider(this).get(WorkingDayViewModel::class.java)
        mWorkingDayRepository.getAllWorkingDayWithWorkCode().observe(viewLifecycleOwner, { workingDay ->
            workingDay.let { it ->
                val hours: ArrayList<Int> = ArrayList()
                var totalHours = 0
                //parcour des données
                it.forEach {
                    //ajout des heures
                    val hour = ((it.realWorkCode.endHour.time - it.realWorkCode.startHour.time)/1000/60/60).toInt()
                    totalHours += hour
                    hours.add(totalHours)
                }
                setData(hours)
                chart.notifyDataSetChanged()
                chart.animateX(500)
            }
        })

        val l = chart.legend
        //Écriture de la légende
        l.form = LegendForm.LINE
        return view
    }

    private fun setData(value: ArrayList<Int>) {
        val values: ArrayList<Entry> = ArrayList()
        var position = 0
        for (i in value) {
            values.add(Entry(position.toFloat(), i.toFloat(), R.drawable.layout_bg))
            position+=1
        }
        val set1: LineDataSet
        if (chart.data != null &&
                chart.data.dataSetCount > 0) {
            set1 = chart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            // Création de datatype et mise en place du type
            set1 = LineDataSet(values, "")
            set1.setDrawIcons(false)
            set1.setDrawValues(false)

            set1.color = resources.getColor(R.color.colorAccent)
            set1.setDrawCircles(false)

            // mise en place du style des lignes
            set1.lineWidth = 3f
            set1.circleRadius = 0f

            set1.label = "Heure de travail"


            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1) // ajout de la donnée au dataset

            // creation de l'objet de data avec le dataset
            val data = LineData(dataSets)

            // set data
            chart.data = data
        }
    }

}