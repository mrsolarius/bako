package app.bako.view.navigation.fragment.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.model.workingday.WorkingDay
import app.bako.model.workingday.WorkingDayDao
import app.bako.model.workingday.WorkingDayRepository
import app.bako.model.workingday.WorkingDayViewModel
import kotlinx.android.synthetic.main.fragment_calendar.view.*

/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        /*val bouton: Button = view.findViewById<Button>(R.id.openCodeManagement)
        bouton.setOnClickListener{
            val activityObject: MainActivity = activity as MainActivity
            activityObject.makeCurrentFragment(CodesListFragment())
        }
        */
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)


        val adapter = CalendarRecyclerAdapter()
        val recyclerView = view.calendarRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val mWorkingDayRepository = ViewModelProvider(this).get(WorkingDayViewModel::class.java)
        mWorkingDayRepository.getAllWorkingDayWithWorkCode().observe(viewLifecycleOwner, { workingDay ->
            workingDay.let {
                adapter.setData(workingDay)
            }
        })
        recyclerView.adapter = adapter
        return view
    }
}